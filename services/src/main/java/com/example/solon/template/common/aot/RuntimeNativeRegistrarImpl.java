package com.example.solon.template.common.aot;

import org.noear.solon.annotation.Component;
import org.noear.solon.aot.RuntimeNativeMetadata;
import org.noear.solon.aot.RuntimeNativeRegistrar;
import org.noear.solon.aot.hint.MemberCategory;
import org.noear.solon.core.AppContext;
import org.noear.solon.core.util.ResourceUtil;

/**
 * @title: native镜像尝鲜，不建议使用
 * @author: trifolium.wang
 * @date: 2024/4/23
 */
@Component
public class RuntimeNativeRegistrarImpl implements RuntimeNativeRegistrar {
    @Override
    public void register(AppContext context, RuntimeNativeMetadata metadata) {
//        metadata.registerReflection("org.noear.solon.serialization.prop.JsonProps", MemberCategory.values());
//        metadata.registerReflection("org.noear.solon.serialization.prop.JsonProps", MemberCategory.values());
//        ResourceUtil.scanResources("classpath:a/**/*").forEach(metadata::registerResourceInclude);
    }
}
