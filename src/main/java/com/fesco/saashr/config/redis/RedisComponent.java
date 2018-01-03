package com.fesco.saashr.config.redis;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fesco.saashr.web.util.JsonMapper;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.BoundHashOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.util.CollectionUtils;

import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author WangXingYu
 * @date 2018-01-02
 */
@Configuration
@EnableConfigurationProperties(RedisSettings.class)
public class RedisComponent {

    @Autowired
    private RedisSettings redisSettings;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setDatabase(redisSettings.getDatabase());
        jedisConnectionFactory.setHostName(redisSettings.getHost());
        jedisConnectionFactory.setPort(redisSettings.getPort());
        jedisConnectionFactory.setPassword(redisSettings.getPassword());
        return jedisConnectionFactory;
    }

    @Autowired
    private JedisConnectionFactory jedisConnectionFactory;

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(jedisConnectionFactory);
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }

    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;


    /**
     * 删除缓存 <br>
     * 根据 key 精确匹配删除
     *
     * @param key
     */
    @SuppressWarnings("unchecked")
    public void del(String... key) {
        if (key != null && key.length > 0) {
            if (key.length == 1) {
                redisTemplate.delete(key[0]);
            } else {
                redisTemplate.delete(CollectionUtils.arrayToList(key));
            }
        }
    }

    /**
     * 批量删除 <br>
     * （该操作会执行模糊查询，请尽量不要使用，以免影响性能或误删）
     *
     * @param pattern
     */
    public void batchDel(String... pattern) {
        for (String kp : pattern) {
            redisTemplate.delete(redisTemplate.keys(kp + "*"));
        }
    }

    /**
     * 取得缓存（int 型）
     *
     * @param key
     * @return
     */
    public Integer getInt(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isNotBlank(value)) {
            return Integer.valueOf(value);
        }
        return null;
    }

    /**
     * 取得缓存（字符串类型）
     *
     * @param key
     * @return
     */
    public String getStr(String key) {
        return stringRedisTemplate.boundValueOps(key).get();
    }

    /**
     * 取得缓存（字符串类型）
     *
     * @param key
     * @return
     */
    public String getStr(String key, boolean retain) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return value;
    }

    /**
     * 获取缓存 <br>
     * 注：基本数据类型 (Character 除外)，请直接使用 get(String key, Class<T> clazz) 取值
     *
     * @param key
     * @return
     */
    public Object getObj(String key) {
        return redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存 <br>
     * 注：java 8 种基本类型的数据请直接使用 get(String key, Class<T> clazz) 取值
     *
     * @param key
     * @param retain 是否保留
     * @return
     */
    public Object getObj(String key, boolean retain) {
        Object obj = redisTemplate.boundValueOps(key).get();
        if (!retain) {
            redisTemplate.delete(key);
        }
        return obj;
    }

    /**
     * 获取缓存 <br>
     * 注：该方法暂不支持 Character 数据类型
     *
     * @param key   key
     * @param clazz 类型
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T get(String key, Class<T> clazz) {
        return (T) redisTemplate.boundValueOps(key).get();
    }

    /**
     * 获取缓存 json 对象 <br>
     *
     * @param key   key
     * @param clazz 类型
     * @return
     */
    public <T> T getJson(String key, Class<T> clazz) {
        return JsonMapper.fromJsonString(stringRedisTemplate.boundValueOps(key).get(), clazz);
    }

    /**
     * 将 value 对象写入缓存
     *
     * @param key
     * @param value
     * @param expireTime 失效时间 (秒)
     */
    public void set(String key, Object value, long expireTime) {
        if (value.getClass().equals(String.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Integer.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Double.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Float.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Short.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Long.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else if (value.getClass().equals(Boolean.class)) {
            stringRedisTemplate.opsForValue().set(key, value.toString());
        } else {
            redisTemplate.opsForValue().set(key, value);
        }
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 将 value 对象以 JSON 格式写入缓存
     *
     * @param key
     * @param value
     * @param expireTime 失效时间 (秒)
     */
    public void setJson(String key, Object value, long expireTime) {
        stringRedisTemplate.opsForValue().set(key, JsonMapper.toJsonString(value));
        if (expireTime > 0) {
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 更新 key 对象 field 的值
     *
     * @param key   缓存 key
     * @param field 缓存对象 field
     * @param value 缓存对象 field 值
     */
    public void setJsonField(String key, String field, String value) {
        JSONObject obj = JSON.parseObject(stringRedisTemplate.boundValueOps(key).get());
        obj.put(field, value);
        stringRedisTemplate.opsForValue().set(key, obj.toJSONString());
    }


    /**
     * 递减操作
     *
     * @param key
     * @param by
     * @return
     */
    public double decr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * 递增操作
     *
     * @param key
     * @param by
     * @return
     */
    public double incr(String key, double by) {
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 获取 double 类型值
     *
     * @param key
     * @return
     */
    public double getDouble(String key) {
        String value = stringRedisTemplate.boundValueOps(key).get();
        if (StringUtils.isNotBlank(value)) {
            return Double.valueOf(value);
        }
        return 0d;
    }

    /**
     * 设置 double 类型值
     *
     * @param key
     * @param value
     * @param expireTime 失效时间 (秒)
     */
    public void setDouble(String key, double value, long expireTime) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (expireTime > 0) {
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 设置 double 类型值
     *
     * @param key
     * @param value
     * @param expireTime 失效时间 (秒)
     */
    public void setInt(String key, int value, long expireTime) {
        stringRedisTemplate.opsForValue().set(key, String.valueOf(value));
        if (expireTime > 0) {
            stringRedisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 将 map 写入缓存
     *
     * @param key
     * @param map
     * @param expireTime 失效时间 (秒)
     */
    public <T> void setMap(String key, Map<String, T> map, long expireTime) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 将 map 写入缓存
     *
     * @param key
     * @param obj
     * @param expireTime 失效时间 (秒)
     */
    @SuppressWarnings("unchecked")
    public <T> void setMap(String key, T obj, long expireTime) {
        Map<String, String> map = (Map<String, String>) JsonMapper.parseObject(obj, Map.class);
        redisTemplate.opsForHash().putAll(key, map);
    }


    /**
     * 向 key 对应的 map 中添加缓存对象
     *
     * @param key
     * @param map
     */
    public <T> void addMap(String key, Map<String, T> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * 向 key 对应的 map 中添加缓存对象
     *
     * @param key   cache 对象 key
     * @param field map 对应的 key
     * @param value 值
     */
    public void addMap(String key, String field, String value) {
        redisTemplate.opsForHash().put(key, field, value);
    }

    /**
     * 向 key 对应的 map 中添加缓存对象
     *
     * @param key   cache 对象 key
     * @param field map 对应的 key
     * @param obj   对象
     */
    public <T> void addMap(String key, String field, T obj) {
        redisTemplate.opsForHash().put(key, field, obj);
    }

    /**
     * 获取 map 缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    public <T> Map<String, T> mget(String key, Class<T> clazz) {
        BoundHashOperations<String, String, T> boundHashOperations = redisTemplate.boundHashOps(key);
        return boundHashOperations.entries();
    }

    /**
     * 获取 map 缓存
     *
     * @param key
     * @param clazz
     * @return
     */
    public <T> T getMap(String key, Class<T> clazz) {
        BoundHashOperations<String, String, String> boundHashOperations = redisTemplate.boundHashOps(key);
        Map<String, String> map = boundHashOperations.entries();
        return JsonMapper.parseObject(map, clazz);
    }

    /**
     * 获取 map 缓存中的某个对象
     *
     * @param key
     * @param field
     * @param clazz
     * @return
     */
    @SuppressWarnings("unchecked")
    public <T> T getMapField(String key, String field, Class<T> clazz) {
        return (T) redisTemplate.boundHashOps(key).get(field);
    }

    /**
     * 删除 map 中的某个对象
     *
     * @param key   map 对应的 key
     * @param field map 中该对象的 key
     * @author lh
     * @date 2016 年 8 月 10 日
     */
    public void delMapField(String key, String... field) {
        BoundHashOperations<String, String, ?> boundHashOperations = redisTemplate.boundHashOps(key);
        boundHashOperations.delete(field);
    }

    /**
     * 指定缓存的失效时间
     *
     * @param key        缓存 KEY
     * @param expireTime 失效时间 (秒)
     * @author FangJun
     * @date 2016 年 8 月 14 日
     */
    public void expire(String key, long expireTime) {
        if (expireTime > 0) {
            redisTemplate.expire(key, expireTime, TimeUnit.SECONDS);
        }
    }

    /**
     * 添加 set
     *
     * @param key
     * @param value
     */
    public void sadd(String key, String... value) {
        redisTemplate.boundSetOps(key).add(value);
    }

    /**
     * 删除 set 集合中的对象
     *
     * @param key
     * @param value
     */
    public void srem(String key, String... value) {
        redisTemplate.boundSetOps(key).remove(value);
    }

    /**
     * set 重命名
     *
     * @param oldkey
     * @param newkey
     */
    public void srename(String oldkey, String newkey) {
        redisTemplate.boundSetOps(oldkey).rename(newkey);
    }

    /**
     * 短信缓存
     *
     * @param key
     * @param value
     * @param time
     * @author fxl
     * @date 2016 年 9 月 11 日
     */
    public void setIntForPhone(String key, Object value, int time) {
        stringRedisTemplate.opsForValue().set(key, JsonMapper.toJsonString(value));
        if (time > 0) {
            stringRedisTemplate.expire(key, time, TimeUnit.SECONDS);
        }
    }

    /**
     * 模糊查询 keys
     *
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    //    -------------------------------------------------------------------------------
    public void set(String key, String value) {
        ValueOperations<String, String> ops = this.stringRedisTemplate.opsForValue();
        boolean existent = this.stringRedisTemplate.hasKey(key);
        if (existent) {
            System.out.println("this key is not existent!");
        } else {
            ops.set(key, value);
        }
    }

    public String get(String key) {
        return this.stringRedisTemplate.opsForValue().get(key);
    }

    public void del(String key) {
        this.stringRedisTemplate.delete(key);
    }
}