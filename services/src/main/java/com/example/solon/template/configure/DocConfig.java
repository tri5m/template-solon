package com.example.solon.template.configure;

import com.github.xiaoymin.knife4j.solon.extension.OpenApiExtensionResolver;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.docs.DocDocket;
import org.noear.solon.docs.models.ApiScheme;

/**
 * @title: DocConfig
 * @author: trifolium.wang
 * @date: 2024/5/15
 */
@Configuration
public class DocConfig {

    @Inject
    OpenApiExtensionResolver openApiExtensionResolver;

    /**
     * 简单点的
     */
    @Bean("appApi")
    public DocDocket appApi() {
        //根据情况增加 "knife4j.setting" （可选）
        return new DocDocket()
                .basicAuth(openApiExtensionResolver.getSetting().getBasic())
                .vendorExtensions(openApiExtensionResolver.buildExtensions())
                .groupName("app端接口")
                .schemes(ApiScheme.HTTP.toValue())
                .apis("com.example.demo");

    }

    /**
     * 丰富点的
     */
//    @Bean("adminApi")
//    public DocDocket adminApi() {
//        //根据情况增加 "knife4j.setting" （可选）
//        return new DocDocket()
//                .basicAuth(openApiExtensionResolver.getSetting().getBasic())
//                .vendorExtensions(openApiExtensionResolver.buildExtensions())
//                .groupName("admin端接口")
//                .info(new ApiInfo().title("在线文档")
//                        .description("在线API文档")
//                        .termsOfService("https://gitee.com/noear/solon")
//                        .contact(new ApiContact().name("demo")
//                                .url("https://gitee.com/noear/solon")
//                                .email("demo@foxmail.com"))
//                        .version("1.0"))
//                .schemes(ApiScheme.HTTP.toValue(), ApiScheme.HTTPS.toValue())
//                .globalResponseInData(true)
//                .globalResult(Result.class)
//                .apis("com.example.demo"); //可以加多条，以包名为单位
//
//    }
}
