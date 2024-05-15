package com.example.solon.template.common.aop;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.aspect.Interceptor;
import org.noear.solon.core.aspect.Invocation;
import org.noear.solon.core.handle.Context;

/**
 * @title: 权限过滤注解拦截器
 * @author: trifolium.wang
 * @date: 2024/5/15
 */
@Slf4j
@Component
public class TokenValidatorInterceptor implements Interceptor {


    @Override
    public Object doIntercept(Invocation inv) throws Throwable {
        TokenValidator anno = inv.method().getAnnotation(TokenValidator.class);

        checkToken(anno, Context.current());

        return inv.invoke();
    }

    public void checkToken(TokenValidator tokenValidator, Context ctx) {

        log.info("模拟检测token");

    }
}
