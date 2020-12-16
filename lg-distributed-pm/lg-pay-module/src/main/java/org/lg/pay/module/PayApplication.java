package org.lg.pay.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.retry.annotation.EnableRetry;
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
@EnableRetry
@EnableScheduling
@EnableAsync
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("org.lg.pay.module.dao")
public class PayApplication {
	
	public static void main(String[] args) {
		SpringApplication.run(PayApplication.class, args);
	}
	
}
