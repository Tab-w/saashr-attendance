package com.fesco.saashr.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
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

    /**
     * 添加缓存数据（给定key已存在，进行覆盖）
     *
     * @param key
     * @param value
     * @throws DataAccessException
     */
    public void set(final Object key, final Object value) throws DataAccessException {
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.set(getKeyBytes(key), getValueBytes(value));
                return null;
            }
        });
    }

    /**
     * 添加缓存数据（给定key已存在，不进行覆盖，直接返回false）
     *
     * @param key
     * @param value
     * @return 操作成功返回true，否则返回false
     * @throws DataAccessException
     */
    public boolean setNX(final Object key, final Object value) throws DataAccessException {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.setNX(getKeyBytes(key), getValueBytes(value));
            }
        });
        return result;
    }

    /**
     * 添加缓存数据，设定缓存失效时间
     *
     * @param key
     * @param value
     * @param expireSeconds 过期时间，单位 秒
     * @throws DataAccessException
     */
    public void setEx(final Object key, final Object value, final long expireSeconds) throws DataAccessException {
        redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                connection.setEx(getKeyBytes(key), expireSeconds, getValueBytes(value));
                return true;
            }
        });
    }

    /**
     * 添加缓存数据，设定默认缓存失效时间
     *
     * @param key
     * @param value
     * @throws DataAccessException
     */
    public void setEx(Object key, Object value) throws DataAccessException {
        setEx(key, value, expireTime);
    }

    /**
     * 获取key对应value
     *
     * @param key
     * @return
     * @throws DataAccessException
     */
    public <T> T get(final Object key) throws DataAccessException {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.get(getKeyBytes(key));
            }
        });
        if (result == null) {
            return null;
        }
        return deserializeValue(result);
    }

    public boolean hSet(final Object key, final Object field, final Object value) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hSet(getKeyBytes(key), getHashKeyBytes(field), getHashValueBytes(value));
            }
        });
        return result;
    }

    public <T> T hGet(final Object key, final Object field) throws DataAccessException {
        byte[] result = redisTemplate.execute(new RedisCallback<byte[]>() {
            @Override
            public byte[] doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.hGet(getKeyBytes(key), getHashKeyBytes(field));
            }
        });
        if (result == null) {
            return null;
        }
        return deserializeHashValue(result);
    }

    /**
     * 设置超时时间
     *
     * @param key
     * @param seconds
     * @return
     */
    public boolean expire(final Object key, final long seconds) {
        boolean result = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.expire(getKeyBytes(key), seconds);
            }
        });

        return result;
    }

    /**
     * 删除指定key数据
     *
     * @param key
     * @return 返回操作影响记录数
     */
    public long del(final Object key) {
        if (key == null) {
            return 0l;
        }
        Long delNum = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.del(getKeyBytes(key));
            }
        });
        return delNum;
    }

    /**
     * 用于删除哈希表 key 中的一个或多个指定字段，不存在的字段将被忽略
     *
     * @param key   哈希表对应的键
     * @param filed 哈希表中要删除的键
     * @return
     */
    public Long hdel(final Object key, final Object... filed) {
        if (key == null) {
            return 0l;
        }
        final byte[] keys = getKeyBytes(key);
        if (keys == null || keys.length == 0) {
            return 0L;
        }
        Long delNum = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection connection) throws DataAccessException {
                List<byte[]> fieldList = new ArrayList<byte[]>();
                for (Object f : filed) {
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
            }
        });
        return delNum;
    }

    public Set<byte[]> keys(final Object key) {
        if (key == null) {
            return null;
        }
        Set<byte[]> bytesSet = redisTemplate.execute(new RedisCallback<Set<byte[]>>() {
            @Override
            public Set<byte[]> doInRedis(RedisConnection connection) throws DataAccessException {
                return connection.keys(getKeyBytes(key));
            }
        });

        return bytesSet;
    }

    public void flushDb() {
        redisTemplate.execute(new RedisCallback<Void>() {
            @Override
            public Void doInRedis(RedisConnection redisConnection) throws DataAccessException {
                redisConnection.flushDb();
                return null;
            }
        });
    }

    /**
     * size
     */
    public Long dbSize() {
        Long dbSize = redisTemplate.execute(new RedisCallback<Long>() {
            @Override
            public Long doInRedis(RedisConnection redisConnection) throws DataAccessException {
                return redisConnection.dbSize();
            }
        });
        return dbSize;
    }
}