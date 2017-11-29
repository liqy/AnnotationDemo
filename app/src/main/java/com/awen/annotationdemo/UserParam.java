package com.awen.annotationdemo;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * 此注解作用在参数上
 */
@Target(PARAMETER)
@Retention(RUNTIME)
public @interface UserParam {
    String name() default "";

    String phone() default "";
}
