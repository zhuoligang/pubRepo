package com.lg.datadispose.module.conf.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSONObject;
import com.lg.datadispose.module.bean.dto.ResultDTO;
import com.lg.datadispose.module.constant.AuthContants;

/**
 * 
* @ClassName: MyAuthenticationFailureHandler
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zlg
* @date 2019年9月20日下午5:39:11
*
 */
@Component
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		String message = AuthContants.LOGIN_FAILE;
		int code = AuthContants.CODE_500;
		if (AuthContants.VERIFY_CODE_ERROR.equals(exception.getMessage())) {
			message = AuthContants.VERIFY_CODE_ERROR;
			code = AuthContants.CODE_603;
		}
		// 返回
		ResultDTO<?> resultDTO = new ResultDTO<>(code, message);
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().write(JSONObject.toJSONString(resultDTO).toCharArray());
	}

}
