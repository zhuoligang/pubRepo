package com.lg.datadispose.module.conf.auth;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.lg.datadispose.module.constant.AuthContants;

/**
 * 
* @ClassName: MyAuthenticationSuccessHandler
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zlg
* @date 2019年9月20日下午5:39:17
*
 */
@Component
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
	private final static Logger logger = LoggerFactory.getLogger(MyAuthenticationSuccessHandler.class);
	
	@Value(value = "${prefix.auth}")
	private String authPrefix;

	@Value(value = "${oauth.redirectUrl}")
	private String redirectUrl;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		String deviceType = request.getHeader("deviceType");
		logger.info("访问设备-----------》》》" + deviceType);
		if ("browser".equals(deviceType)) {
			response.sendRedirect(this.authPrefix + AuthContants.BROWSER_AUTHENTICATION_SUCCESS_URL + this.redirectUrl);
		} else if ("app".equals(deviceType)) {
			response.sendRedirect(this.authPrefix + AuthContants.APP_AUTHENTICATION_SUCCESS_URL + this.redirectUrl);
		}
	}

}
