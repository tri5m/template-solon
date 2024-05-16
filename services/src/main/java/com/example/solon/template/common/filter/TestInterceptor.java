package com.example.solon.template.common.filter;

import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Handler;
import org.noear.solon.core.route.PathRule;
import org.noear.solon.core.route.RouterInterceptor;
import org.noear.solon.core.route.RouterInterceptorChain;

/**
 * @title: 测试过滤器，优先级小于filter， 环绕型
 * <p>
 * 纯 Interceptor 是对Handler(Handler controller)反射处理
 * 所有类型的拦截器对静态资源无效
 * <p>
 * 如果直接拦截不进行处理，则在 ctx.setHandled(true);后直接返回，不需要doIntercept了
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
@Slf4j
@Component
public class TestInterceptor implements RouterInterceptor {

    @Override
    public PathRule pathPatterns() {
        return new PathRule().include("/**");
    }

    @Override
    public void doIntercept(Context ctx, Handler mainHandler, RouterInterceptorChain chain) throws Throwable {
        log.debug("走了过滤器--前");
        chain.doIntercept(ctx, mainHandler);
        log.debug("走了过滤器--后");
    }
}
