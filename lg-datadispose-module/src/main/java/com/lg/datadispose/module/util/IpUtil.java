package com.lg.datadispose.module.util;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IpUtil {

	private final static Logger logger = LoggerFactory.getLogger(IpUtil.class);
	public static String getIpAddr(HttpServletRequest request) {
		String ip = request.getHeader("x-forwarded-for");
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getHeader("WL-Proxy-Client-IP");
		}
		if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
			ip = request.getRemoteAddr();
		}
		return ip;
	}

	public static String getRealIpAddr(HttpServletRequest request) {
		String ip = getIpAddr(request);
		logger.info("`````` ip is :`````````"+ip);
		if (ip.indexOf(",") == -1) {
			return ip;
		}
		return ip.substring(0, ip.indexOf(","));
	}
	
}
