package com.example.solon.template.util;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;

import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * @title: JsonUtils
 * @author: trifolium.wang
 * @date: 2024/5/6
 */
public class JsonUtils {

    /**
     * json转换工具
     */
    private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();

    static {
        // 该特性决定了当遇到未知属性（没有映射到属性，没有任何setter或者任何可以处理它的handler），是否应该抛出一个JsonMappingException异常。
        OBJECT_MAPPER.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    }

    /**
     * 传入一个对象转化为json字符串并写入字节输出流
     */
    @SneakyThrows
    public static void write(OutputStream outputStream, Object param) {
        OBJECT_MAPPER.writeValue(outputStream, param);
    }

    /**
     * 将对象转为json字符串
     */
    @SneakyThrows
    public static String toJson(Object object) {
        return OBJECT_MAPPER.writeValueAsString(object);
    }

    /**
     * 获取json对象节点
     */
    @SneakyThrows
    public static JsonNode getJsonNode(String content) {
        return OBJECT_MAPPER.readTree(content);
    }

    /**
     * 将json字符串反序列化为一个Bean对象
     */
    @SneakyThrows
    public static <T> T toBean(String jsonString, Class<T> valueType) {
        return OBJECT_MAPPER.readValue(jsonString, valueType);
    }

    /**
     * 将json字符串数组反序列化为一个List对象
     */
    @SneakyThrows
    public static <T> List<T> toList(String jsonString, Class<T> valueType) {
        JavaType javaType = getCollectionType(ArrayList.class, valueType);
        return OBJECT_MAPPER.readValue(jsonString, javaType);
    }

    /**
     * 获取泛型的 Collection Type
     */
    public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return OBJECT_MAPPER.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
}
