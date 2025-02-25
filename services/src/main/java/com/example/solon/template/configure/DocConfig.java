package com.example.solon.template.configure;

import com.example.solon.template.model.common.response.ResponseResult;
import com.github.xiaoymin.knife4j.solon.extension.OpenApiExtensionResolver;
import org.noear.solon.annotation.Bean;
import org.noear.solon.annotation.Configuration;
import org.noear.solon.annotation.Inject;
import org.noear.solon.docs.DocDocket;
import org.noear.solon.docs.models.ApiContact;
import org.noear.solon.docs.models.ApiInfo;
import org.noear.solon.docs.models.ApiScheme;

/**
 * @title: DocConfig
 * @author: trifolium.wang
 * @date: 2024/5/15
 */
@Configuration
public class DocConfig {

    @Inject
    private OpenApiExtensionResolver openApiExtensionResolver;


    @Bean("appApi")
    public DocDocket appApi() {
        //根据情况增加 "knife4j.setting" （可选）
        return new DocDocket()
                .basicAuth(openApiExtensionResolver.getSetting().getBasic())
                .vendorExtensions(openApiExtensionResolver.buildExtensions())
                .groupName("app端接口")
                .info(new ApiInfo().title("在线文档")
                        .description("在线API文档")
                        .contact(new ApiContact().name("trifolium.wang")
                                .url("https://github.com/trifolium-x")
                                .email("trifolium.wang@gmail.com"))
                        .version("1.0"))
                .schemes(ApiScheme.HTTP.toValue())
                .globalResponseInData(true)
                .globalResult(ResponseResult.class)
                .apis("com.example.solon");
    }

}
