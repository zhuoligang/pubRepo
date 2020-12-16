/**
 * Md5.java com.ejie.nbrs.util
 * 
 * Function： TODO
 * 
 * ver date author ────────────────────────────────── ver1.1 2017年8月15日 torreswang
 * 
 * Copyright (c) 2017, EJie All Rights Reserved.
 */

package com.bi.activity.util;

import java.security.MessageDigest;
import java.util.UUID;

/**
 * ClassName:Md5
 * 
 * @author torreswang
 * @version
 * @since Ver 1.1
 * @Date 2017年8月15日 下午12:13:47
 * @see
 */

/**
 * Created by Sunchp
 */
public class TokenGenerator {
  private static final char[] hexCode = "0123456789abcdef".toCharArray();

  public static void main(String[] args) {
    System.out.println(TokenGenerator
        .generateValue("sadfasdhfojwq3irj1pijh4eo21u34h12jb32`b143b2`1h3`14l3`1bn3"));
    System.out.println(TokenGenerator.generateValue());
    System.out.println(TokenGenerator.generateValue());
  }

  public static String toHexString(byte[] data) {
    if (data == null) {
      return null;
    }
    StringBuilder r = new StringBuilder(data.length * 2);
    for (byte b : data) {
      r.append(hexCode[(b >> 4) & 0xF]);
      r.append(hexCode[(b & 0xF)]);
    }
    return r.toString();
  }

  public static String generateValue() {
    return generateValue(UUID.randomUUID().toString());
  }

  public static String generateValue(String param) {
    try {
      MessageDigest algorithm = MessageDigest.getInstance("MD5");
      algorithm.reset();
      algorithm.update(param.getBytes());
      byte[] messageDigest = algorithm.digest();
      return toHexString(messageDigest);
    } catch (Exception e) {
      throw new RuntimeException("Token cannot be generated.", e);
    }
  }
}
