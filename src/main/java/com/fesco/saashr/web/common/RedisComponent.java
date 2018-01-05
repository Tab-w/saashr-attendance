package com.fesco.saashr.web.common;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author: WangXingYu
 * @date: 2018-01-04
 */
@Component
public class RedisComponent {
    /**
     * 过期时间，默认30分钟
     */
    private Long expireTime = 1000 * 60 * 30L;

    /**
     * Redis模版
     */
    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    public void setExpireTime(long expireTime) {
        this.expireTime = expireTime;
    }

    /**
     * 添加缓存数据（给定key已存在，进行覆盖）
     *
     * @param key   key
     * @param value value
     * @throws DataAccessException
     */
    public void set(final Object key, final Object value) throws DataAccessException {
        redisTemplate.execute((RedisCallback<Void>) connection -> {
            connection.set(getKeyBytes(key), getValueBytes(value));
            return null;
        });
    }

    /**
     * 添加缓存数据（给定key已存在，不进行覆盖，直接返回false）
     *
     * @param key   key
     * @param value value
     * @return 操作成功返回true，否则返回false
     * @throws DataAccessException
     */
    public boolean setNX(final Object key, final Object value) throws DataAccessException {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.setNX(getKeyBytes(key), getValueBytes(value)));
    }

    /**
     * 添加缓存数据，设定默认缓存失效时间
     *
     * @param key   key
     * @param value value
     * @throws DataAccessException
     */
    public void setEx(Object key, Object value) throws DataAccessException {
        setEx(key, value, expireTime);
    }

    /**
     * 添加缓存数据，设定缓存失效时间
     *
     * @param key           key
     * @param value         value
     * @param expireSeconds 过期时间，单位 秒
     * @throws DataAccessException
     */
    public void setEx(final Object key, final Object value, final long expireSeconds) throws DataAccessException {
        redisTemplate.execute((RedisCallback<Boolean>) connection -> {
            connection.setEx(getKeyBytes(key), expireSeconds, getValueBytes(value));
            return true;
        });
    }

    /**
     * 获取key对应value
     *
     * @param key
     * @return
     * @throws DataAccessException
     */
    public <T> T get(final Object key) throws DataAccessException {
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.get(getKeyBytes(key)));
        if (result == null) {
            return null;
        }
        return deserializeValue(result);
    }

    /**
     * 添加Map缓存数据（给定key已存在，进行覆盖）
     *
     * @param key   redis的key
     * @param field map的field
     * @param value value
     * @return
     */
    public void hSet(final Object key, final Object field, final Object value) {
        redisTemplate.execute((RedisCallback<Void>) redisConnection -> {
            redisConnection.hSet(getKeyBytes(key), getHashKeyBytes(field), getHashValueBytes(value));
            return null;
        });
    }

    /**
     * 获取Map中指定字段的值。
     *
     * @param key
     * @param field
     * @return
     * @throws DataAccessException
     */
    public <T> T hGet(final Object key, final Object field) throws DataAccessException {
        byte[] result = redisTemplate.execute((RedisCallback<byte[]>) connection -> connection.hGet(getKeyBytes(key), getHashKeyBytes(field)));
        if (result == null) {
            return null;
        }
        return deserializeHashValue(result);
    }


    /**
     * 用于删除Map中的一个或多个指定字段，不存在的字段将被忽略
     *
     * @param key   redis的key
     * @param field map的field
     * @return
     */
    public Long hdel(final Object key, final Object... field) {
        if (key == null) {
            return 0L;
        }
        final byte[] keys = getKeyBytes(key);
        if (keys == null || keys.length == 0) {
            return 0L;
        }
        Long delNum = redisTemplate.execute((RedisCallback<Long>) connection -> {
            List<byte[]> fieldList = new ArrayList<>();
            for (Object f : field) {
                if (f != null) {
                    byte[] hfs = getHashKeyBytes(f);
                    if (hfs != null && hfs.length > 0) {
                        fieldList.add(hfs);
                    }
                }
            }
            if (fieldList.size() == 0) {
                return 0L;
            }
            byte[][] bytes = new byte[0][];
            return connection.hDel(keys, fieldList.toArray(bytes));
        });
        return delNum;
    }

    /**
     * 查询符合条件的缓存
     *
     * @param pattern
     * @return
     */
    public List<String> keys(final String pattern) {
        if (pattern == null) {
            return null;
        }
        Set<byte[]> bytesSet = redisTemplate.execute((RedisCallback<Set<byte[]>>) connection -> connection.keys(getKeyBytes(pattern)));
        List<String> list = new ArrayList<>(bytesSet.size());
        for (byte[] bytes : bytesSet) {
            list.add(new String(bytes));
        }
        return list;
    }

    /**
     * 删除指定key数据
     *
     * @param key key
     * @return 返回操作影响记录数
     */
    public long del(final Object key) {
        if (key == null) {
            return 0L;
        }
        return redisTemplate.execute((RedisCallback<Long>) connection -> connection.del(getKeyBytes(key)));
    }

    /**
     * 设置超时时间
     *
     * @param key           key
     * @param expireSeconds 过期时间，单位 秒
     * @return
     */
    public boolean expire(final Object key, final long expireSeconds) {
        return redisTemplate.execute((RedisCallback<Boolean>) connection -> connection.expire(getKeyBytes(key), expireSeconds));
    }

    /**
     * 清空当前数据库中的所有key
     */
    public void flushDb() {
        redisTemplate.execute((RedisCallback<Void>) redisConnection -> {
            redisConnection.flushDb();
            return null;
        });
    }

    /**
     * 获取当前数据库的key的数量
     */
    public Long dbSize() {
        return redisTemplate.execute((RedisCallback<Long>) redisConnection -> redisConnection.dbSize());
    }

    private RedisSerializer<Object> getKeySerializer() {
        return (RedisSerializer<Object>) redisTemplate.getKeySerializer();
    }

    private RedisSerializer<Object> getValueSerializer() {
        return (RedisSerializer<Object>) redisTemplate.getValueSerializer();
    }

    private RedisSerializer<Object> getHashKeySerializer() {
        return (RedisSerializer<Object>) redisTemplate.getHashKeySerializer();
    }

    private RedisSerializer<Object> getHashValueSerializer() {
        return (RedisSerializer<Object>) redisTemplate.getHashValueSerializer();
    }

    private byte[] getKeyBytes(Object key) {
        return getKeySerializer().serialize(key);
    }

    private byte[] getHashKeyBytes(Object key) {
        return getHashKeySerializer().serialize(key);
    }

    private byte[] getValueBytes(Object value) {
        return getValueSerializer().serialize(value);
    }

    private byte[] getHashValueBytes(Object value) {
        return getHashValueSerializer().serialize(value);
    }

    private <T> T deserializeValue(byte[] bytes) {
        return (T) getValueSerializer().deserialize(bytes);
    }

    private <T> T deserializeHashValue(byte[] bytes) {
        return (T) getHashValueSerializer().deserialize(bytes);
    }
}