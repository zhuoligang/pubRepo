package com.lg.web.module.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.lg.web.module.bean.bo.SecretBO;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.util.CombinationEncryptionUtil;
import com.lg.web.module.util.RSACoder;

@Service
public class SecretService {

	private final static Logger logger = LoggerFactory.getLogger(SecretService.class);

	@Value(value = "${rsa.privateKey}")
	private String privateKey;

	@Value(value = "${des.timeLimit}")
	private Integer timeLimit;

	public <T> T decryptpParameter(String ciphertext, Class<T> clazz) {
		String cleartext = "";
		try {
			cleartext = new String(
					RSACoder.decryptByPrivateKey(Base64.decodeBase64(ciphertext), Base64.decodeBase64(privateKey)));
			T value = JSONObject.parseObject(cleartext, clazz);
			return value;
		} catch (Exception e) {
			logger.error(WebConstant.DECRYPTP_PARAMETER_ERROR, e);
			throw new BusinessException(WebConstant.CODE_500, WebConstant.DECRYPTP_PARAMETER_ERROR);
		}
	}

	@SuppressWarnings("unchecked")
	public <T> T decryptpParameter(SecretBO secretBO, Class<T> clazz) {
		try {
			// 解密des秘钥
			String secretKey = new String(RSACoder.decryptByPrivateKey(Base64.decodeBase64(secretBO.getKey()),
					Base64.decodeBase64(privateKey)));
			// 解密时间戳
			Long timestamp = Long.parseLong(
					new String(CombinationEncryptionUtil.decrypt(Base64.decodeBase64(secretBO.getTime()), secretKey)));
			// 判断与当前时间的间隙
			if (System.currentTimeMillis() - timestamp > timeLimit) {
				logger.error(WebConstant.TIME_INTERVAL_IS_TOO_LONG);
				throw new BusinessException(WebConstant.CODE_500, WebConstant.DECRYPTP_PARAMETER_ERROR);
			}
			// 解密数据
			String cleartext = new String(
					CombinationEncryptionUtil.decrypt(Base64.decodeBase64(secretBO.getCiphertext()), secretKey));
			T value;
			try {
				value = JSONObject.parseObject(cleartext, clazz);
			} catch (Exception e) {
				value = (T) cleartext;
			}
			return value;
		} catch (Exception e) {
			logger.error(WebConstant.DECRYPTP_PARAMETER_ERROR, e);
			throw new BusinessException(WebConstant.CODE_500, WebConstant.DECRYPTP_PARAMETER_ERROR);
		}
	}
	
	@SuppressWarnings("unchecked")
	public Map<String, String> decryptpParameter(Map<String, String> map) {
		Map<String, String> resultMap = new HashMap<String, String>();
		try {
			// 得到验证参数
			String note = map.get("note");
			// 得到业务参数
			String ciphertext = map.get("ciphertext");
			SecretBO secretBO = JSON.parseObject(ciphertext, SecretBO.class);
			String cleartext = null;
			// 如果有验证码，校验参数解密过期时间以后生成的加密参数校验时间为准，即校验note参数的加密时间
			if (note != null && !"".equals(note)) {
				// 解密验证参数，并设置
				SecretBO secretBO_ = JSON.parseObject(note, SecretBO.class);
				String clearNote = decryptpDo(secretBO_, true);
				Map<String, String> parseObject2 = JSONObject.parseObject(clearNote, Map.class);
				for (Entry<String, String> entry : parseObject2.entrySet()) {
					resultMap.put(entry.getKey(), String.valueOf(entry.getValue()));
				}
				// 有验证参数的情况，不需要验证参数加密时间
				cleartext = decryptpDo(secretBO, false);
			} else {
				// 只有业务参数的情况，需要验证参数加密时间
				cleartext = decryptpDo(secretBO, true);
			}
			// 解密业务参数，并设置
			Map<String, String> parseObject = JSONObject.parseObject(cleartext, Map.class);
			for (Entry<String, String> entry : parseObject.entrySet()) {
				resultMap.put(entry.getKey(), String.valueOf(entry.getValue()));
			}

			return resultMap;
		} catch (Exception e) {
			logger.error(WebConstant.DECRYPTP_PARAMETER_ERROR, e);
			throw new BusinessException(WebConstant.CODE_500, WebConstant.DECRYPTP_PARAMETER_ERROR);
		}
	}

	public String decryptpDo(SecretBO secretBO, boolean isCheckTime) {
		try {
			// 解密des秘钥
			String secretKey = new String(RSACoder.decryptByPrivateKey(Base64.decodeBase64(secretBO.getKey()),
					Base64.decodeBase64(privateKey)));
			if (isCheckTime) {
				// 解密时间戳
				Long timestamp = Long.parseLong(new String(
						CombinationEncryptionUtil.decrypt(Base64.decodeBase64(secretBO.getTime()), secretKey)));
				// 判断与当前时间的间隙
				if (System.currentTimeMillis() - timestamp > 300000) {
					logger.error(WebConstant.TIME_INTERVAL_IS_TOO_LONG);
					throw new BusinessException(WebConstant.CODE_500,
							WebConstant.DECRYPTP_PARAMETER_ERROR);
				}
			}
			// 解密数据
			String cleartext = new String(
					CombinationEncryptionUtil.decrypt(Base64.decodeBase64(secretBO.getCiphertext()), secretKey));
			return cleartext;
		} catch (Exception e) {
			logger.error(WebConstant.DECRYPTP_PARAMETER_ERROR, e);
			throw new BusinessException(WebConstant.CODE_500, WebConstant.DECRYPTP_PARAMETER_ERROR);
		}
	}

	// 公钥
	private static final String PUBLIC_KEY = "MIGfMA0GCSqGSIb3DQEBAQUAA4GNADCBiQKBgQDn/QnUfkKkQpG++6YDUYo8qc0mrcRbRsmokp3EJZJrwIblldv6W2+yAIWkkel4GzsL+oklZReqZcG4F+ug3otc6IZa3fFMXuaDzoDzicdOABe2s8igLjMcfhujI9MqYvkVVSyFWdVOLNAnzQJgkisnHKZGPQZcxnQAHxTOUco+LwIDAQAB";

	/*
	 * 加密
	 */
	public String encryptParameter(Map<String, String> map) {
		try {
			// 生成随机8位数字符串作为本次加密des的密钥
			String key_des = RandomStringUtils.randomAlphanumeric(8);
//			System.out.println("生成密匙：" + key_des);
			
			// des加密数据
			String ciphertext_ = CombinationEncryptionUtil.encrypt(JSONObject.toJSONString(map).getBytes(), key_des);
//			System.out.println("加密后的数据：" + ciphertext_);

			// des加密时间戳
			String time_ = CombinationEncryptionUtil.encrypt(String.valueOf(System.currentTimeMillis()).getBytes(), key_des);
//			System.out.println("加密后的时间戳：" + new String(time_));
			
			// rsa加密des秘钥
			String key_des_ = Base64.encodeBase64String(RSACoder.encryptByPublicKey(key_des.getBytes(), Base64.decodeBase64(PUBLIC_KEY)));
//			System.out.println("加密后的密钥：" + new String(key_des_));

			SecretBO secretBO = new SecretBO();
			secretBO.setCiphertext(ciphertext_);
			secretBO.setKey(key_des_);
			secretBO.setTime(time_);
			return JSONObject.toJSONString(secretBO);
		} catch (Exception e) {
			logger.error(WebConstant.DECRYPTP_PARAMETER_ERROR, e);
			throw new BusinessException(WebConstant.CODE_500, WebConstant.DECRYPTP_PARAMETER_ERROR);
		}
	}
}
