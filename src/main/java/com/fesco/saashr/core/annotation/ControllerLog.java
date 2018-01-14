package com.fesco.saashr.core.annotation;

import java.lang.annotation.*;

/**
 * @author WangXingYu
 * @date 2018-01-10
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ControllerLog {

    /**
     * 模块
     **/
    String module() default "";

    /**
     * 操作
     **/
    String operation();

    /**
     * 描述
     **/
    String description() default "无";
}