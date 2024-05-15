package com.example.solon.template.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.route.PathRule;
import org.noear.solon.core.route.RouterInterceptor;
import org.noear.solon.core.route.RouterInterceptorChain;

/**
 * @title: AuthFilter
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
@Slf4j
@Component
public class AuthFilter implements RouterInterceptor {

    @Override
    public PathRule pathPatterns() {
        return new PathRule().include("/**");
    }

    @Override
    public void doIntercept(Context ctx, Handler mainHandler, RouterInterceptorChain chain) throws Throwable {
        log.info("走了过滤器");
    }
}
