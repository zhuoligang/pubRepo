/**
 * CheckoutNumUtil.java com.ejie.ecip.util
 * 
 * Function： TODO
 * 
 * ver date author ────────────────────────────────── ver1.0 2017年12月8日 zlg
 * 
 * Copyright (c) 2017, EJie All Rights Reserved.
 */

package com.bi.activity.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:CheckoutNumUtil 数字校验工具类
 * 
 * @author zlg
 * @version Ver 1.0
 * @Date 2017年12月8日 下午7:02:30
 * @see
 */
public class CheckoutNumUtil {
  public static void main(String[] args) {
    String a = "asd123";
    System.out.println(HasLetter(a));
  }

  // 判断一个字符串是否都为数字
  // public boolean isDigit(String strNum) {
  // return strNum.matches("[0-9]{1,}");
  // }

  /**
   * 该方法主要使用正则表达式来判断字符串中是否包含字母
   * 
   * @param cardNum 待检验的原始卡号
   * @return 返回是否包含
   */
  public static boolean HasLetter(String content) {
    String regex = ".*[a-zA-Z]+.*";
    Matcher m = Pattern.compile(regex).matcher(content);
    return m.matches();
  }

  // 判断一个字符串是否都为数字
  public static boolean isDigit(String strNum) {
    Pattern pattern = Pattern.compile("[0-9]{1,}");
    Matcher matcher = pattern.matcher((CharSequence) strNum);
    return matcher.matches();
  }

  // 截取数字
  public static String getNumbers(String content) {
    Pattern pattern = Pattern.compile("\\d+");
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
      return matcher.group(0);
    }
    return "";
  }

  // 截取非数字
  public static String splitNotNumber(String content) {
    Pattern pattern = Pattern.compile("\\D+");
    Matcher matcher = pattern.matcher(content);
    while (matcher.find()) {
      return matcher.group(0);
    }
    return "";
  }

  // 判断一个字符串是否含有数字
  public static boolean HasDigit(String content) {
    boolean flag = false;
    Pattern p = Pattern.compile(".*\\d+.*");
    Matcher m = p.matcher(content);
    if (m.matches()) {
      flag = true;
    }
    return flag;
  }

}
