package com.lg.web.module.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 接口限流
 * @author zlg
 *
 */
@Inherited
@Documented
@Target({ElementType.FIELD,ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface VisitLimit {
    //标识 指定sec时间段内的访问次数限制
    int limit() default 5;  
    //标识 时间段
    long sec() default 5;
}