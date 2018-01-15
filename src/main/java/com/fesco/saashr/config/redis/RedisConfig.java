package com.fesco.saashr.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import java.io.Serializable;

/**
 * @author: WangXingYu
 * @date: 2018-01-03
 */
@Configuration
public class RedisConfig {

    @Autowired
    private Environment environment;

    @Bean
    public JedisConnectionFactory jedisConnectionFactory() {
        JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
        jedisConnectionFactory.setDatabase(environment.getProperty("spring.redis.database", Integer.class));
        jedisConnectionFactory.setHostName(environment.getProperty("spring.redis.host"));
        jedisConnectionFactory.setPort(environment.getProperty("spring.redis.port", Integer.class));
        jedisConnectionFactory.setPassword(environment.getProperty("spring.redis.password"));
        return jedisConnectionFactory;
    }

    @Bean
    public RedisTemplate<Serializable, Serializable> redisTemplate() {
        RedisTemplate<Serializable, Serializable> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(this.jedisConnectionFactory());
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        return redisTemplate;
    }
}