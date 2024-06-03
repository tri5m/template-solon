package com.example.solon.template;

import com.example.solon.template.common.util.DateUtil;
import lombok.extern.slf4j.Slf4j;
import org.noear.solon.Solon;
import org.noear.solon.annotation.SolonMain;
import org.noear.solon.web.cors.CrossFilter;

/**
 * @title: App
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
@Slf4j
@SolonMain
public class App {
    public static void main(String[] args) {

        log.info("应用启动标记====> solonVersion:{}, time:{}", Solon.version(), DateUtil.getDatetimeStr());
        Solon.start(App.class, args, app -> {
            log.info("启动环境====> env:{}", app.cfg().env());
            app.filter(-1, new CrossFilter().allowedOrigins("*"));
        });
    }
}