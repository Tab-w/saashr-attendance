package com.fesco.saashr.core.annotation;

import java.lang.annotation.*;

/**
 * @author WangXingYu
 * @date 2018-01-10
 */
@Target({ElementType.PARAMETER, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface Log {

    /**
     * 要执行的操作类型比如：add操作
     **/
    String type() default "";

    /**
     * 要执行的具体操作比如：添加用户
     **/
    String description() default "";
}