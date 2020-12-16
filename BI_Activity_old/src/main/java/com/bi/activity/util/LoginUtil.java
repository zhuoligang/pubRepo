package com.bi.activity.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import com.alibaba.fastjson.JSONObject;

/**
 * ClassName:LoginUtil（登录工具类）
 * 
 * @author hzw
 * @version Ver 1.0
 * @Date 2018年1月24日 下午3:43:07
 * @see
 */
public class LoginUtil {
  // private final static String POST_URL_CheckLog =
  // "http://sso.ejie365.cn:8080/checkLogin";

  // private final static String POST_URL_CheckLog = "http://192.168.1.200:8080/sso/checkLogin";
  // private final static String SSO_URL = ConfigConst.SSO_URL;

  /**
   * 接口调用 GET
   */
  public static void httpURLConectionGET(String urlStr, String jsonStr) {
    httpURLConnectionPOST(urlStr, jsonStr);
  }

  /**
   * 接口调用 POST
   */
  public static String httpURLConnectionPOST(String urlStr, String jsonStr) {
    StringBuilder sb = new StringBuilder(); // 用来存储响应数据
    try {
      URL url = new URL(urlStr);
      // 将url 以 open方法返回的urlConnection 连接强转为HttpURLConnection连接
      // (标识一个url所引用的远程对象连接)
      HttpURLConnection connection = (HttpURLConnection) url.openConnection();// 此时cnnection只是为一个连接对象,待连接中
      // 设置连接输出流为true,默认false (post 请求是以流的方式隐式的传递参数)
      connection.setDoOutput(true);
      // 设置连接输入流为true
      connection.setDoInput(true);
      // 设置请求方式为post
      connection.setRequestMethod("POST");
      // post请求缓存设为false
      connection.setUseCaches(false);
      // 设置该HttpURLConnection实例是否自动执行重定向
      connection.setInstanceFollowRedirects(true);
      // 设置请求头里面的各个属性
      // application/x-javascript text/xml->xml数据
      // application/x-javascript->json对象
      // application/x-www-form-urlencoded->表单数据
      connection.setRequestProperty("contentType", "application/json");
      // 建立连接
      // (请求未开始,直到connection.getInputStream()方法调用时才发起,以上各个参数设置需在此方法之前进行)
      connection.connect();
      // 创建输入输出流,用于往连接里面输出携带的参数,(输出内容为?后面的内容)
      OutputStream outStream = connection.getOutputStream();
      // 写入请求的字符串
      outStream.write((jsonStr.toString()).getBytes());
      outStream.flush();
      outStream.close();
      // System.out.println(connection.getResponseCode());
      // 连接发起请求,处理服务器响应 (从连接获取到输入流并包装为bufferedReader)
      BufferedReader bf =
          new BufferedReader(new InputStreamReader(connection.getInputStream(), "UTF-8"));
      String line;
      // 循环读取流,若不到结尾处
      while ((line = bf.readLine()) != null) {
        // sb.append(bf.readLine());
        sb.append(line).append(System.getProperty("line.separator"));
      }
      bf.close(); // 重要且易忽略步骤 (关闭流,切记!)
      connection.disconnect(); // 销毁连接
      System.out.println(sb.toString());

    } catch (Exception e) {
      e.printStackTrace();
    }
    return sb.toString();
  }

  /**
   * 校验用户token 成功返回userId 失败返回 null
   * 
   * @param token
   * @return
   */
  public static String checkLoginPost(String token) {
    JSONObject jsonStr = new JSONObject();
    jsonStr.put("token", token);
    System.out.println(jsonStr);
    try {
      ConfigConst.getConfigConst();
    } catch (Exception e) {
      e.printStackTrace();
    }
    String POST_URL_CheckLog = ConfigConst.POST_URL_CheckLog;
    System.out.println(POST_URL_CheckLog);
    String resultStr = httpURLConnectionPOST(POST_URL_CheckLog, jsonStr.toString());
    try {
      JSONObject resultJson = JSONObject.parseObject(resultStr);
      String dataStr = resultJson.getString("data");
      JSONObject dataJson = JSONObject.parseObject(dataStr);
      String userId = dataJson.getString("userId");
      return userId;
    } catch (Exception e) {
      // TODO: handle exception
      return null;
    }
  }

  // token
  public static void main(String[] args) {
    System.out.println(checkLoginPost("5af4c4320f6b359c49c54dcb5a09e998"));
  }
}
