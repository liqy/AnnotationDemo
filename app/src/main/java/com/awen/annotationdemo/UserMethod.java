package com.awen.annotationdemo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 定义一个只有一个参数的注解，作用在方法上
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface UserMethod {
    String title() default "";
}
