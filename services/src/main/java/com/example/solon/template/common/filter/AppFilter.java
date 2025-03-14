package com.example.solon.template.common.filter;

import com.example.solon.template.common.helper.exception.AppException;
import com.example.solon.template.model.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.exception.StatusException;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;

/**
 * @title: 全局的异常处理，全局过滤器，优先级最高，环绕型
 * <p>
 * request -> Filter -> StaticResource -> RouterInterceptor
 * -> Action(Handler controller)  -> RouterInterceptor后  -> Filter后  -> response
 * <p>控制器里的 @Mapping 函数，即为 Action</p>
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
@Slf4j
@Component
public class AppFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {
//            if("json".equals(ctx.contentType())){
//                ctx.headerMap().put("Content-Type", "application/json");
//            }
            chain.doFilter(ctx);
        } catch (StatusException e) {
            if (e.getCode() == 404) {
                ctx.render(ResponseResult.error("资源不存在", e.getCode()));
            } else if (e.getCode() == 405) {
                ctx.render(ResponseResult.error("不支持的http方法:" + ctx.method(), e.getCode()));
            } else if (e.getCode() == 400) {
                log.warn(e.getMessage());
                ctx.render(ResponseResult.error(e.getMessage(), e.getCode()));
            } else {
                log.error(e.getMessage(), e);
                ctx.render(ResponseResult.error(e.getMessage(), e.getCode()));
            }
        } catch (AppException e) {
            ctx.render(ResponseResult.error(e.getMessage(), -1));
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            ctx.status(500);
            ctx.render(ResponseResult.error("服务器内部错误!", -1));
        }
    }
}
