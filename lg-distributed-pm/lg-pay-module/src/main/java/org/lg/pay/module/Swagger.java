package org.lg.pay.module;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * 
* @ClassName: Swagger
* @Description: TODO(这里用一句话描述这个类的作用)
* @author zlg
* @date 2019年5月29日下午3:38:19
*
 */
@Configuration
@EnableSwagger2
public class Swagger {

	@Value("${swagger2.enable}")
	private boolean swagger2Enable;

	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2)
				.enable(swagger2Enable)
				.apiInfo(apiInfo())
				.select()
				// 为当前包路径
				.apis(RequestHandlerSelectors.basePackage("org.lg.pay.module.controller"))
				.paths(PathSelectors.any()).build();
	}

	// 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("示例API")
				// 创建人
				.contact(new Contact("Lg", "http://www.baidu.com", ""))
				// 版本号
				.version("1.0")
				// 描述
				.description("API 描述").build();
	}
}
