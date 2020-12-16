/**
 * DisposeUtil.java com.ejie.ecip.util
 * 
 * Function： TODO
 * 
 * ver date author ────────────────────────────────── ver1.0 2017年12月30日 zlg
 * 
 * Copyright (c) 2017, EJie All Rights Reserved.
 */

package com.bi.activity.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * ClassName:DisposeUtil 遍历对象属性值(利用反射实现)，可以在需要对 对象中的每个字段都执行相同的处理时使用 ；（转换包含｛｝，包含||的字段）
 * 
 * @author zlg
 * @version Ver 1.0
 * @Date 2017年12月30日 下午2:07:15
 * @see
 */
public class DisposeUtil {
  public static void main(String[] args) {
    // Account account = new Account();
    // account.setStatus("12||地方都十分明显才女");
    // account
    // .setStatusRemark("{江苏省}{南京市}{江宁区}{320000}{320100}{320115}江宁区东山街道上元大街420号江宁万达广场（西区）2幢1809室");
    // account.setUserId(1041);
    // System.out.println(dispose(account));
    // String str = "12||地方都十分明显才女";
    // System.out.println(doHb(str));
    // String str0 = "{江苏省}{南京市}{江宁区}{320000}{320100}{320115}江宁区东山街道上元大街420号江宁万达广场（西区）2幢1809室";
    // System.out.println(doAdress(str0));
    Date date = new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
    String newTime = sdf.format(date);
    System.out.println(newTime);

    System.out.println(stringToDate(newTime));
  }

  /**
   * 
   * dispose:(Describe the function of this method)
   * 
   * @Description: TODO 反射操作字段
   * @param object
   * @return
   * @throws
   */
  public static Object dispose(Object object) {
    Field[] field = object.getClass().getDeclaredFields(); // 获取实体类的所有属性，返回Field数组
    try {
      for (int j = 0; j < field.length; j++) { // 遍历所有属性
        String name = field[j].getName(); // 获取属性的名字
        name = name.substring(0, 1).toUpperCase() + name.substring(1); // 将属性的首字符大写，方便构造get，set方法
        String type = field[j].getGenericType().toString(); // 获取属性的类型
        if (type.equals("class java.lang.String")) { // 如果type是类类型，则前面包含"class "，后面跟类名
          Method m = object.getClass().getMethod("get" + name);
          String value = (String) m.invoke(object); // 调用getter方法获取属性值
          // .....处理开始........
          // 执行处理方法
          value = doHb(value);
          value = doAdress(value);
          // .....处理结束........
          m = object.getClass().getMethod("set" + name, String.class);
          m.invoke(object, value);
        } else if (type.equals("class java.util.Date")) {
          Method m = object.getClass().getMethod("get" + name);
          Date value = (Date) m.invoke(object);
          // .....处理开始........
          // 执行处理方法
          value = dateToDate(value);
          // .....处理结束........
          m = object.getClass().getMethod("set" + name, Date.class);
          m.invoke(object, value);
        }
        // else if (type.equals("class java.lang.Integer")) {
        // Method m = object.getClass().getMethod("get" + name);
        // Integer value = (Integer) m.invoke(object);
        // if (value == null) {
        // m = object.getClass().getMethod("set" + name, Integer.class);
        // m.invoke(object, value);
        // }
        // } else if (type.equals("class java.lang.Boolean")) {
        // Method m = object.getClass().getMethod("get" + name);
        // Boolean value = (Boolean) m.invoke(object);
        // if (value == null) {
        // m = object.getClass().getMethod("set" + name, Boolean.class);
        // m.invoke(object, false);
        // }
        // }else {
        // Method m = object.getClass().getMethod("get" + name);
        // Object ob = m.invoke(object);
        //
        // m = object.getClass().getMethod("set" + name, String.class);
        // m.invoke(object, ob);
        // }
        // 如果有需要,可以仿照上面继续进行扩充,再增加对其它类型的判断
      }
    } catch (NoSuchMethodException e) {
      e.printStackTrace();
    } catch (SecurityException e) {
      e.printStackTrace();
    } catch (IllegalAccessException e) {
      e.printStackTrace();
    } catch (IllegalArgumentException e) {
      e.printStackTrace();
    } catch (InvocationTargetException e) {
      e.printStackTrace();
    }

    return object;
  }

  /**
   * 
   * doAdress:(Describe the function of this method)
   * 
   * @Description: TODO 地址处理方法
   * @param str0
   * @return
   * @throws
   */
  public static String doAdress(String str0) {
    // String str0 =
    // "{江苏省}{南京市}{江宁区}{320000}{320100}{320115}江宁区东山街道上元大街420号江宁万达广场（西区）2幢1809室";

    // 获得第一个{的位置
    String result = str0;
    if (str0 == null || "".equals(str0)) {

    } else {
      if (str0.contains("{")) {
        int index = str0.indexOf("{");

        index = str0.indexOf("{", index + 1);
        int i = index;
        String str1_1 = str0.substring(0, index);

        index = str0.indexOf("{", index + 1);
        int k = index;
        String str1_2 = str0.substring(i, index);

        index = str0.lastIndexOf("}");
//        index = str0.indexOf("{", index + 1);
//        String str1_3 = str0.substring(k, index);
        String str1_3 = str0.substring(k+1, index);
        
        Pattern p = Pattern.compile("#");
        Matcher m = p.matcher(str1_3);
        if (m.find())
          str1_3 = "";
        
        String str1 = "";
        if (str1_1.equals(str1_2)) {
          str1 = str1_2 + str1_3;
        } else {
          str1 = str1_1 + str1_2 + str1_3;
        }

        // 再取3次{,其实是第四次出现{的位置
        // for (int i = 0; i < 3; i++) {
        // index = str0.indexOf("{", index + 1);
        // }
        // String str1 = str0.substring(0, index);

        String str2 = str1.replace("{", "").replace("}", "").replace("#", "");
        // System.out.println(str2);
        String str3 = str0.substring(str0.lastIndexOf("}") + 1, str0.length());
        result = str2 + str3;
        // System.out.println(result);
      }

    }

    return result;
  }

  /**
   * 
   * doHb:(Describe the function of this method)
   * 
   * @Description: TODO 分割，保留最后一个“|”后面的字符
   * @param str
   * @return
   * @throws
   */
  public static String doHb(String str) {
    if (str != null && !"".equals(str)) {
      if (str.contains("|")) {
        str = str.substring(str.lastIndexOf("|") + 1, str.length());
        // System.out.println(str);
      }
    } else {

    }
    return str;
  }

  public static Date stringToDate(String str) {
    DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
    Date date = null;
    try {
      // Fri Feb 24 00:00:00 CST 2012
      date = format.parse(str);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    // 2012-02-24
    date = java.sql.Date.valueOf(str);

    return date;
  }

  public static Date dateToDate(Date date0) {
    if (date0 != null && !"".equals(date0)) {
      SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
      String newTime = sdf.format(date0);
      date0 = stringToDate(newTime);
    } else {

    }
    return date0;
  }


}
