package com.fesco.saashr.web.util;

import com.alibaba.fastjson.JSON;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser.Feature;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.*;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.fasterxml.jackson.module.jaxb.JaxbAnnotationModule;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.TimeZone;

/**
 * @author: WangXingYu
 * @date: 2018-01-03
 */
public class JsonMapper extends ObjectMapper {

    private static final long serialVersionUID = 1L;
    private final static Logger LOGGER = LoggerFactory.getLogger(JsonMapper.class);

    private static JsonMapper mapper;

    public JsonMapper() {
        this(Include.NON_EMPTY);
    }

    public JsonMapper(Include include) {
        // 设置输出时包含属性的风格
        if (include != null) {
            this.setSerializationInclusion(include);
        }
        // 设置输入时忽略在 JSON 字符串中存在但 Java 对象实际没有的属性
        this.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
        // 空值处理为空串
        this.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>() {
            @Override
            public void serialize(Object value, JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException,
                    JsonProcessingException {
                jgen.writeString("");
            }
        });
        // 进行 HTML 解码。
        this.registerModule(new SimpleModule().addSerializer(String.class, new JsonSerializer<String>() {
            @Override
            public void serialize(String value, JsonGenerator jgen,
                                  SerializerProvider provider) throws IOException,
                    JsonProcessingException {
                jgen.writeString(StringEscapeUtils.unescapeHtml4(value));
            }
        }));
        // 设置时区
        this.setTimeZone(TimeZone.getDefault());//getTimeZone("GMT+8:00")
    }

    /**
     * 创建只输出非 Null 且非 Empty(如 List.isEmpty) 的属性到 Json 字符串的 Mapper, 建议在外部接口中使用.
     */
    public static JsonMapper getInstance() {
        if (mapper == null) {
            mapper = new JsonMapper().enableSimple();
        }
        return mapper;
    }

    /**
     * 创建只输出初始值被改变的属性到 Json 字符串的 Mapper, 最节约的存储方式，建议在内部接口中使用。
     */
    public static JsonMapper nonDefaultMapper() {
        if (mapper == null) {
            mapper = new JsonMapper(Include.NON_DEFAULT);
        }
        return mapper;
    }

    /**
     * Object 可以是 POJO，也可以是 Collection 或数组。
     * 如果对象为 Null, 返回 "null".
     * 如果集合为空集合, 返回 "[]".
     */
    public String toJson(Object object) {
        try {
            return this.writeValueAsString(object);
        } catch (IOException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("write to json string error:" + object, e);
            }
            return null;
        }
    }

    /**
     * 反序列化 POJO 或简单 Collection 如 List<String>.
     * <p>
     * 如果 JSON 字符串为 Null 或 "null" 字符串, 返回 Null.
     * 如果 JSON 字符串为 "[]", 返回空集合.
     * <p>
     * 如需反序列化复杂 Collection 如 List<MyBean>, 请使用 fromJson(String,JavaType)
     *
     * @see #fromJson(String, JavaType)
     */
    public <T> T fromJson(String jsonString, Class<T> clazz) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return this.readValue(jsonString, clazz);
        } catch (IOException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("parse json string error:" + jsonString, e);
            }
            return null;
        }
    }

    /**
     * 反序列化复杂 Collection 如 List<Bean>, 先使用函數 createCollectionType 构造类型, 然后调用本函数.
     *
     * @see #createCollectionType(Class, Class...)
     */
    @SuppressWarnings("unchecked")
    public <T> T fromJson(String jsonString, JavaType javaType) {
        if (StringUtils.isEmpty(jsonString)) {
            return null;
        }
        try {
            return (T) this.readValue(jsonString, javaType);
        } catch (IOException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("parse json string error:" + jsonString, e);
            }
            return null;
        }
    }

    /**
     * 構造泛型的 Collection Type 如:
     * ArrayList<MyBean>, 则调用 constructCollectionType(ArrayList.class,MyBean.class)
     * HashMap<String,MyBean>, 则调用 (HashMap.class,String.class, MyBean.class)
     */
    public JavaType createCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {
        return this.getTypeFactory().constructParametricType(collectionClass, elementClasses);
    }

    /**
     * 當 JSON 裡只含有 Bean 的部分屬性時，更新一個已存在 Bean，只覆蓋該部分的屬性.
     */
    @SuppressWarnings("unchecked")
    public <T> T update(String jsonString, T object) {
        try {
            return (T) this.readerForUpdating(object).readValue(jsonString);
        } catch (JsonProcessingException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("update json string:" + jsonString + "to object:" + object + "error.", e);
            }
        } catch (IOException e) {
            if (LOGGER.isWarnEnabled()) {
                LOGGER.warn("update json string:" + jsonString + "to object:" + object + "error.", e);
            }
        }
        return null;
    }

    /**
     * 輸出 JSONP 格式數據.
     */
    public String toJsonP(String functionName, Object object) {
        return toJson(new JSONPObject(functionName, object));
    }

    /**
     * 設定是否使用 Enum 的 toString 函數來讀寫 Enum,
     * 為 False 時時使用 Enum 的 name() 函數來讀寫 Enum, 默認為 False.
     * 注意本函數一定要在 Mapper 創建後, 所有的讀寫動作之前調用.
     */
    public JsonMapper enableEnumUseToString() {
        this.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
        this.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
        return this;
    }

    /**
     * 支持使用 Jaxb 的 Annotation，使得 POJO 上的 annotation 不用与 Jackson 耦合。
     * 默认会先查找 jaxb 的 annotation，如果找不到再找 jackson 的。
     */
    public JsonMapper enableJaxbAnnotation() {
        JaxbAnnotationModule module = new JaxbAnnotationModule();
        this.registerModule(module);
        return this;
    }

    /**
     * 允许单引号
     * 允许不带引号的字段名称
     */
    public JsonMapper enableSimple() {
        this.configure(Feature.ALLOW_SINGLE_QUOTES, true);
        this.configure(Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        return this;
    }

    /**
     * 取出 Mapper 做进一步的设置或使用其他序列化 API.
     */
    public ObjectMapper getMapper() {
        return this;
    }

    /**
     * 对象转换为 JSON 字符串
     *
     * @param object
     * @return
     */
    public static String toJsonString(Object object) {
        return JsonMapper.getInstance().toJson(object);
    }

    /**
     * JSON 字符串转换为对象
     *
     * @param jsonString
     * @param clazz
     * @return
     */
    public static <T> T fromJsonString(String jsonString, Class<T> clazz) {
        return JsonMapper.getInstance().fromJson(jsonString, clazz);
    }


    /**
     * 将 obj 对象转换成 class 类型的对象
     *
     * @param obj
     * @param clazz
     * @return
     */
    public static <T> T parseObject(Object obj, Class<T> clazz) {
        return JSON.parseObject(JSON.toJSONString(obj), clazz);
    }

}