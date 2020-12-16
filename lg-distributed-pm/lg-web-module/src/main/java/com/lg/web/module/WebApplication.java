package com.lg.web.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
* @ClassName: LGApplication
* @Description: TODO(资源模块启动程序)
* @author zlg
* @date 2019年5月29日下午3:38:03
*
 */
@EnableScheduling
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.lg.web.module.dao")
public class WebApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(WebApplication.class, args);
	}
	
}
