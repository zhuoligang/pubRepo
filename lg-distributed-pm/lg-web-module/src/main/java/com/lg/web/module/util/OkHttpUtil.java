package com.lg.web.module.util;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 
 * @ClassName: OkHttpUtil
 * @Description: TODO(简单的封装)
 * @author zlg
 * @date 2019年6月10日下午3:16:12
 *
 */
public class OkHttpUtil {

	public static String postForJson(String url, String json) {
		String str = null;
		MediaType mediaType = MediaType.parse("application/json");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(mediaType, json);
		Request request = new Request.Builder().url(url).post(body).build();
		try {
			Response response = client.newCall(request).execute();
			str = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return str;
	}

	public static String getForJson(String url, String json){
		String s = null;
		MediaType mediaType = MediaType.parse("application/json");
		OkHttpClient client = new OkHttpClient();
		RequestBody body = RequestBody.create(mediaType, json);
		Request request = new Request.Builder().url(url).post(body).build();
		Response response;
		try {
			response = client.newCall(request).execute();
			s = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

	public static String getWeather(String url) {
		String s = null;
		OkHttpClient client = new OkHttpClient();
		Request request = new Request.Builder().url(url).build();
		try {
			Response response = client.newCall(request).execute();
			s = response.body().string();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return s;
	}

}
