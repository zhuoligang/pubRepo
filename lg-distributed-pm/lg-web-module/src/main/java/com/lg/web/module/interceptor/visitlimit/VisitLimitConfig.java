package com.lg.web.module.interceptor.visitlimit;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * VisitLimit MVC配置
 *
 */
@Configuration
public class VisitLimitConfig implements WebMvcConfigurer {
    
    @Autowired
    private VisitLimitInterceptor accessLimitInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        //限流
        registry.addInterceptor(accessLimitInterceptor).addPathPatterns("/vl/**");
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> argumentResolvers) {
    	
    }
}