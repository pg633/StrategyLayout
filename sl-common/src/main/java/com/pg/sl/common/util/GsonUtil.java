package com.pg.sl.common.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author lianzheng04
 * @version 1.0
 * @date 2020/5/3 1:09 下午
 */
@Slf4j
public class GsonUtil {
    public static ObjectMapper objectMapper;

    static {
        if (objectMapper == null) {
            objectMapper = new ObjectMapper();
            // 禁用空对象转换json校验
            objectMapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
    }

    public static String toJson(Object value) {
        try {
            return objectMapper.writeValueAsString(value);
        } catch (JsonProcessingException e) {
            log.error("GsonUtil.toJson error", e.getMessage());
        }
        return null;
    }

    public static <T> T toObj(String str, Class<T> cls) {
        if (StringUtils.isEmpty(str)) {
            return null;
        }
        try {
            return objectMapper.readValue(str, cls);
        } catch (Exception e) {
            log.error("GsonUtil.toObj error", e.getMessage());
        }
        return null;
    }
    private static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return objectMapper.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }
    @SuppressWarnings("unchecked")
    public static <T> List<T> toList(String json, Class<T> beanClass) {
        try {

            return (List<T>)objectMapper.readValue(json, getCollectionType(List.class, beanClass));
        } catch (Exception e) {
            log.error(e.getMessage(),e);
        }
        return null;
    }

    public static List<String> toList(String json) {
        return toList(json,String.class);
    }

    public static <K,V> Map<K, V> toMap(String src, TypeReference<Map<K, V>> t) {
        if(src != null && !"".equals(src)){
            try {
                Map<K, V> result = objectMapper.readValue(src, t);
                return result;
            } catch (Exception e) {
                log.error(e.getMessage(),e);
            }
        }
        return Collections.emptyMap();
    }

}
