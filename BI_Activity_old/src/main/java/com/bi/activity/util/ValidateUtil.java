/**
 * 
 */
package com.bi.activity.util;

import java.math.BigDecimal;

/***
 * @Title: ValidateUtil
 * @Description: ecrs191
 * @packageName com.ejie.nbrs.util
 * @fileName ValidateUtil.java
 * @Company: ejie
 * @version 1.1
 * @author hisahe
 * @date 2017年9月5日 上午10:17:33
 */
public class ValidateUtil {


	// 校验数字长度
	public static boolean validateNumber(Object obj, int len) {
		if (obj != null) {
			String str = String.valueOf(obj);
			int index = str.lastIndexOf(".");
			if (index != -1) {
				System.out.println(index);
				if (index > len) {
					return false;
				} else {
					return true;
				}
			} else {
				if (str.length() > len) {
					return false;
				} else {
					return true;
				}
			}
		} else {
			return true;
		}
	}

	//校验字符串长度 对象 最大长度
public static boolean validateString(Object obj, int len){
		if(obj!=null){
			String str = String.valueOf(obj);
			if(str.length()>len){
				return false;
			}else{
				return true;
			}
		}else{
			return true;
		}
	}
	
}
