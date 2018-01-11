package com.fesco.saashr.core.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.JSONLibDataFormatSerializer;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializerFeature;

import java.util.List;
import java.util.Map;

/**
 * @author: WangXingYu
 * @date: 2018-01-04
 */
public class JsonUtil {

    private static final SerializeConfig CONFIG;

    static {
        CONFIG = new SerializeConfig();
        CONFIG.put(java.util.Date.class, new JSONLibDataFormatSerializer());
        CONFIG.put(java.sql.Date.class, new JSONLibDataFormatSerializer());
    }

    /**
     * 设置对空值的处理
     */
    private static final SerializerFeature[] FEATURES = {
            SerializerFeature.WriteMapNullValue,
            SerializerFeature.WriteNullListAsEmpty,
            SerializerFeature.WriteNullNumberAsZero,
            SerializerFeature.WriteNullBooleanAsFalse,
            SerializerFeature.WriteNullStringAsEmpty,
    };

    /**
     * object to json
     *
     * @param object 要转换的Object
     * @return 转换后的Json
     */
    public static String object2json(Object object) {
        return JSON.toJSONString(object, CONFIG, FEATURES);
    }

    /**
     * json string to object
     *
     * @param json  要转换的Json
     * @param clazz 转换的对象类型
     * @return 转换后的对象
     */
    public static <T> T json2object(String json, Class<T> clazz) {
        return JSON.parseObject(json, clazz);
    }

    /**
     * object array to json
     *
     * @param objects 要转换的Object数组
     * @return 转换后的Json
     */
    public static String array2json(Object[] objects) {
        return JSON.toJSONString(objects);
    }

    /**
     * json string to object array
     *
     * @param json  要转换的Json
     * @param clazz 转换的对象类型
     * @param ts    存放对象的数组
     * @return 转换后的对象数组
     */
    public static <T> T[] json2array(String json, Class<T> clazz, T[] ts) {
        return JSON.parseArray(json, clazz).toArray(ts);
    }

    /**
     * list to json
     *
     * @param list 要转换的List
     * @return 转换后的Json
     */
    public static String list2json(List list) {
        return JSON.toJSONString(list);
    }

    /**
     * json to list
     *
     * @param json  要转换的Json
     * @param clazz 转换的对象类型
     * @return 转换后的对象集合
     */
    public static <T> List<T> json2list(String json, Class<T> clazz) {
        return JSON.parseArray(json, clazz);
    }

    /**
     * map to json
     *
     * @param map 要转换的Map
     * @return 转换后的Json
     */
    public static String map2json(Map map) {
        return JSONObject.toJSONString(map);
    }

    /**
     * json to map
     *
     * @param json 要转换的Json
     * @return 转换后的Map
     */
    public static Map json2map(String json) {
        return JSONObject.parseObject(json);
    }
}