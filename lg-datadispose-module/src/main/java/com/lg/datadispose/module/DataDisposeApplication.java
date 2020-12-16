package com.lg.datadispose.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * 
* @ClassName: DataDisposeApplication
* @Description: TODO(数据处理)
* @author Administrator
* @date 2019年8月2日 15:17:56
*
 */
@EnableScheduling
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("com.lg.datadispose.module.dao")
public class DataDisposeApplication {
	public static void main(String[] args) {
		SpringApplication.run(DataDisposeApplication.class, args);
	}
}
