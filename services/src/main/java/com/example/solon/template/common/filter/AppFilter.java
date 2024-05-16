package com.example.solon.template.common.filter;

import com.example.solon.template.common.helper.exception.AppException;
import com.example.solon.template.common.response.ResponseResult;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.annotation.Component;
import org.noear.solon.core.Constants;
import org.noear.solon.core.handle.Context;
import org.noear.solon.core.handle.Filter;
import org.noear.solon.core.handle.FilterChain;
import org.noear.solon.validation.ValidatorException;
import org.smartboot.http.common.enums.HttpStatus;

/**
 * @title: 全局的异常处理，全局过滤器，优先级最高，环绕型
 * <p>
 * request -> Filter -> StaticResource -> RouterInterceptor
 * -> Action(Handler controller)  -> RouterInterceptor后  -> Filter后  -> response
 *<p>控制器里的 @Mapping 函数，即为 Action</p>
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
@Slf4j
@Component
public class AppFilter implements Filter {

    @Override
    public void doFilter(Context ctx, FilterChain chain) throws Throwable {
        try {

            chain.doFilter(ctx);

            if (!ctx.getHandled()) {
                if (ctx.status() <= 200 && ctx.mainHandler() == null) {
                    // 获取匹配后的原生状态
                    Integer mainStatus = ctx.attr(Constants.mainStatus);
                    ctx.status(mainStatus);
                    switch (mainStatus) {
                        case 404:
                            ctx.render(ResponseResult.error("资源不存在", mainStatus));
                            break;
                        case 405:
                            ctx.render(ResponseResult.error("不支持的http方法:" + ctx.method(), mainStatus));
                            break;
                        default:
                            String des = mainStatus + "";
                            try {
                                HttpStatus httpStatus = HttpStatus.valueOf(mainStatus);
                                if (httpStatus != null) {
                                    des = httpStatus.getReasonPhrase();
                                }
                            } catch (Exception e) {
                                log.warn(e.getMessage(), e);
                            }
                            ctx.render(ResponseResult.error(des, mainStatus));
                    }
                }
            }
        } catch (AppException e) {
            ctx.render(ResponseResult.error(e.getMessage(), -1));
        } catch (ValidatorException e) {
            ctx.status(400);
            ctx.render(ResponseResult.error(e.getMessage(), -1));
        } catch (Throwable e) {
            log.error(e.getMessage(), e);
            ctx.status(500);
            ctx.render(ResponseResult.error("服务器内部错误!", -1));
        }
    }
}
