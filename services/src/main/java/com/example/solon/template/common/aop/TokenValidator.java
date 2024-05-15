package com.example.solon.template.common.aop;

import org.noear.solon.annotation.Around;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @title: 权限过滤注解
 * @author: trifolium.wang
 * @date: 2024/5/15
 */
@Target({ElementType.METHOD, ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
// 自动注入了这个注解
@Around(TokenValidatorInterceptor.class)
public @interface TokenValidator {

    /**
     * 权限数组
     */
    String[] authorities() default {};

    /**
     * 权限是否与
     */
    boolean isAnd() default false;

    /**
     * 是否检查权限
     */
    boolean isCheckPermission() default true;

    /**
     * 是否检查登录
     */
    boolean isCheckLogin() default true;
}
