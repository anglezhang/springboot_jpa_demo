package cn.com.doone.tx.cloud.service.config.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Json转换工具
 */
public class JsonUtils {

    private static ObjectMapper mapper = new ObjectMapper()
            .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
            .setSerializationInclusion(JsonInclude.Include.NON_NULL);

    public static <T> T fromJson(String jsonStr, Class<T> jsonCls) throws IOException {
        if (jsonStr == null)
            return null;
        if (String.class.equals(jsonCls))
            return (T)jsonStr;
        return mapper.readValue(jsonStr, jsonCls);
    }

    public static String toJson(Object object) throws IOException {
        if (object == null)
            return null;
        if (object instanceof String)
            return (String) object;
        return mapper.writeValueAsString(object);
    }

    public static <T> ArrayList<T> fromJsonList(String jsonStr, Class<T> jsonCls) throws IOException {
        if (jsonStr == null)
            return null;

        return mapper.readValue(jsonStr, mapper.getTypeFactory().constructParametrizedType(ArrayList.class, List.class, jsonCls));
    }
}
