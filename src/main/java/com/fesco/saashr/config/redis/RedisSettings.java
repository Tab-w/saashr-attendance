package com.fesco.saashr.config.redis;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: WangXingYu
 * @date: 2018-01-03
 */
@ConfigurationProperties(prefix = "spring.redis")
public class RedisSettings {

    private int database;
    private String host;
    private int port;
    private String password;

    public int getDatabase() {
        return database;
    }

    public void setDatabase(int database) {
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}