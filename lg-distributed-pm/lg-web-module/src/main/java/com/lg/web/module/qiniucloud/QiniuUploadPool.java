package com.lg.web.module.qiniucloud;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.lg.web.module.bean.po.CenterUserMain;
import com.lg.web.module.bean.po.SpecialCertificationInfo;
import com.lg.web.module.constant.WebConstant;
import com.lg.web.module.dao.CenterUserMainMapper;
import com.lg.web.module.dao.SpecialCertificationInfoMapper;
import com.lg.web.module.exception.BusinessException;
import com.lg.web.module.util.WebModuleUtil;

@Component
public class QiniuUploadPool {
	private final static Logger logger = LoggerFactory.getLogger(QiniuUploadPool.class);

	@Autowired
	private QiniuCloudStorageService qiniu;
	@Autowired
	private CenterUserMainMapper centerUserMainMapper;
	@Autowired
	private SpecialCertificationInfoMapper specialCertificationInfoMapper;

	@Async("initTaskAsyncPool")
	public void executeAsync(CenterUserMain centerUserMain, Map<String, byte[]> map) {
		long startTime = System.currentTimeMillis();
		try {
			// 图片上传返回路径
			Map<String, String> resultMap = new HashMap<>();
			for (Entry<String, byte[]> entry : map.entrySet()) {
				try {
					String inputName = entry.getKey();
					byte[] image = entry.getValue();
					logger.info("图片 "+ inputName +"大小为： " + image.length + " k,大约： " + image.length / 1024 + " kb");
					if (image == null || image.length <= 0) {
						continue;
					}
					// 存放七牛云服务器
					String uploadSuffix = qiniu.uploadSuffix(image, ".jpg");
					resultMap.put(inputName, "http://" + uploadSuffix);
					logger.info(inputName + "上传成功!");
				} catch (Exception e) {
					logger.error("图片存放七牛云服务器出现错误", e);
					throw new BusinessException(WebConstant.CODE_500, "图片存放七牛云服务器出现错误");
				}
			}
			SpecialCertificationInfo buildSpecialCertificationInfo = buildSpecialCertificationInfo(centerUserMain,
					resultMap);
			// 新增特殊处理记录
			int i = this.specialCertificationInfoMapper.insertSelective(buildSpecialCertificationInfo);
			if (i < 1) {
				logger.error("高级认证新增特殊处理记录出现错误");
				throw new BusinessException(WebConstant.CODE_500, "高级认证新增特殊处理记录出现错误");
			}
			// 修改高级认证状态
			CenterUserMain cum = new CenterUserMain();
			cum.setId(centerUserMain.getId());
			cum.setIdcardPicCheckType(2);
			cum.setIdcardPicCheckId(1);
			this.centerUserMainMapper.updateByPrimaryKeySelective(cum);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			throw new BusinessException(WebConstant.CODE_500, e.getMessage());
		}
		long endTime = System.currentTimeMillis();
		System.out.println("总共耗时： " + (endTime - startTime));
	}

	public SpecialCertificationInfo buildSpecialCertificationInfo(CenterUserMain centerUserMain,
			Map<String, String> resultMap) {
		SpecialCertificationInfo sc = new SpecialCertificationInfo();
		sc.setId(WebModuleUtil.createUUId());
		sc.setMemberId(centerUserMain.getMemberId());
		sc.setMobile(centerUserMain.getMobile());
		sc.setName(centerUserMain.getName());
		sc.setIdcard(centerUserMain.getIdcard());
		sc.setIdcardPicFront(resultMap.get("idcardPicFront"));
		sc.setIdcardPicBack(resultMap.get("idcardPicBack"));
		sc.setIdcardPicOnhand(resultMap.get("idcardPicOnhand"));
		sc.setReqTime(new Date());
		return sc;
	}

}
