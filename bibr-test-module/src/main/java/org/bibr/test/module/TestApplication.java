package org.bibr.test.module;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
* @ClassName: RushApplication
* @Description: TODO(Bibr抢购活动模块)
* @author Administrator
* @date 2019年4月15日上午11:35:25
*
 */
@SpringBootApplication
@MapperScan("org.bibr.test.module.dao")
public class TestApplication {
	public static void main(String[] args) {
		SpringApplication.run(TestApplication.class, args);
	}
}
