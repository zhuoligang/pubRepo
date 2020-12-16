package com.lg.web.module.filter;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;

import javax.servlet.http.HttpServletRequest;

public class HttpHelper {

	public static String getBodyString(HttpServletRequest request) throws IOException {
		StringBuilder sb = new StringBuilder();
		try (InputStream inputStream = request.getInputStream();
				BufferedReader reader = new BufferedReader(
						new InputStreamReader(inputStream, Charset.forName("UTF-8")));) {
			String line = "";
			while ((line = reader.readLine()) != null) {
				sb.append(line);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return sb.toString();
	}

}
