package com.lg.web.module.conf;

import javax.servlet.MultipartConfigElement;

import org.springframework.boot.web.servlet.MultipartConfigFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FileConfiguration {

	// 设置上传文件限制
	@Bean
	public MultipartConfigElement multipartConfigElement() {
		MultipartConfigFactory config = new MultipartConfigFactory();
		config.setMaxFileSize("20MB");
		config.setMaxRequestSize("100MB");
		return config.createMultipartConfig();
	}
}
