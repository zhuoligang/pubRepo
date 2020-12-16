package com.lg.web.module.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;

import com.lg.web.module.exception.BusinessException;

public class WebModuleUtil {
	
	/**
	 * uuid生产
	 * 
	 * @return
	 */
	public static String createUUId() {
		return UUID.randomUUID().toString().replace("-", "");
	}
	
	/**
	 * 
	* @Title: chooseImageSize
	* @Description: TODO(验证图片大小是否满足设定值)
	* @param @param file
	* @param @return    设定文件
	* @return boolean    返回类型
	* @throws
	 */
	public static boolean chooseImageSize(MultipartFile file,Long maxSize){
		boolean flag = false;
		if(file.getSize() <= maxSize){//10M
			flag = true;
		}
		return flag;
	}
	
	/**
	 * 验证图片后缀
	 * @param suffixs
	 * @return
	 */
	public static boolean isImageSuffixs(String suffixs) {
		String regex = "^(jpg|jpeg|png|JPG|PNG)$";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(suffixs);
		return matcher.matches();
	}

	/**
	 * 日期格式化 日期格式为：yyyy-MM-dd
	 * 
	 * @param date
	 *            日期
	 * @param pattern
	 *            格式，如：DateUtils.DATE_TIME_PATTERN
	 * @return 返回yyyy-MM-dd格式日期
	 */
	public static String format(Date date, String pattern) {
		if (date != null) {
			SimpleDateFormat df = new SimpleDateFormat(pattern);
			return df.format(date);
		}
		return null;
	}
	
	/**
	 * 获取IP地址
	 * 
	 * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
	 * 如果使用了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP地址，X-Forwarded-For中第一个非unknown的有效IP字符串，则为真实IP地址
	 */
	public static String getIpAddr(HttpServletRequest request) {
    	String ip = null;
        try {
            ip = request.getHeader("x-forwarded-for");
	         if (ip != null && ip.length() != 0 && !"unknown".equalsIgnoreCase(ip)) {
	             // 多次反向代理后会有多个ip值，第一个ip才是真实ip
	             if( ip.indexOf(",")!=-1 ){
	                 ip = ip.split(",")[0];
	             }
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("Proxy-Client-IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("WL-Proxy-Client-IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("HTTP_CLIENT_IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("HTTP_X_FORWARDED_FOR");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getHeader("X-Real-IP");
	         }
	         if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
	             ip = request.getRemoteAddr();
	         }
        } catch (Exception e) {
        	throw new BusinessException(500,"获取ip错误！");
        }
        
        return ip;
    }
}
