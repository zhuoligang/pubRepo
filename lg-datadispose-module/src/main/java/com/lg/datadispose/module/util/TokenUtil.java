package com.lg.datadispose.module.util;

import org.springframework.security.jwt.Jwt;
import org.springframework.security.jwt.JwtHelper;

import com.alibaba.fastjson.JSONObject;
import com.lg.datadispose.module.bean.dto.TokenDTO;

/**
 * 
 * @author lsj
 *
 */
public class TokenUtil {

	public static TokenDTO resolveAccessToken(String token) {
		Jwt accessJwt = JwtHelper.decode(token);
		return JSONObject.toJavaObject(JSONObject.parseObject(accessJwt.getClaims()), TokenDTO.class);
	}

}
