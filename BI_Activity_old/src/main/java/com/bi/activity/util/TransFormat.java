package com.bi.activity.util;


import java.io.UnsupportedEncodingException;

/**
 * 
 * ClassName:TransFormat
 * 
 * @author torreswang
 * @version
 * @since Ver 1.1
 * @Date 2017 2017年8月10日 下午7:25:34
 * @see
 */
public class TransFormat {
  public static void main(String[] args) throws Exception {}

  public static String unicode2GB(String strIn) {
    String strOut = null;
    if (strIn == null || strIn.trim().equals("")) {
      return strIn;
    }
    try {
      byte b[] = strIn.getBytes("ISO8859_1");
      strOut = new String(b, "GBK");
    } catch (UnsupportedEncodingException unsupportedencodingexception) {
    }
    return strOut;
  }

  public static String GB2unicode(String strIn) {
    String strOut = null;
    if (strIn == null || strIn.trim().equals("")) {
      return strIn;
    }
    try {
      byte b[] = strIn.getBytes("GBK");
      strOut = new String(b, "ISO8859_1");
    } catch (Exception exception) {
    }
    return strOut;
  }

  public static String GB2utf8(String strIn) {
    String strOut = null;
    if (strIn == null || strIn.trim().equals("")) {
      return strIn;
    }
    try {
      byte b[] = strIn.getBytes("GBK");
      strOut = new String(b, "UTF-8");
      b = strOut.getBytes("UTF-8");
      strOut = new String(b, "GBK");
    } catch (UnsupportedEncodingException unsupportedencodingexception) {
    }
    return strOut;
  }

  public static String utf82GB(String strIn) {
    String strOut = null;
    if (strIn == null || strIn.trim().equals("")) {
      return strIn;
    }
    try {
      // String str = new String(strIn,"GBK");
      byte b[] = strIn.getBytes("UTF-8");
      strOut = new String(b, "GBK");
      b = strOut.getBytes("GBK");
      strOut = new String(b, "UTF-8");
    } catch (Exception exception) {
    }
    return strOut;
  }

  public static String toUtf8String(String s) {
    StringBuffer sb = new StringBuffer();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (c >= 0 && c <= 255) {
        sb.append(c);
      } else {
        byte[] b;
        try {
          b = Character.toString(c).getBytes("utf-8");
        } catch (Exception ex) {
          System.out.println(ex);
          b = new byte[0];
        }
        for (int j = 0; j < b.length; j++) {
          int k = b[j];
          if (k < 0) {
            k += 256;
          }
          sb.append("%" + Integer.toHexString(k).toUpperCase());
        }
      }
    }
    return sb.toString();
  }

}
