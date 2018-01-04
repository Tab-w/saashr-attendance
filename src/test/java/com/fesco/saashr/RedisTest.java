package com.fesco.saashr;

import com.fesco.saashr.config.redis.RedisComponent;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author WangXingYu
 * @date 2018-01-04
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class RedisTest {
    @Autowired
    private RedisComponent redisManager;

    @Test
    public void testGet() {
        redisManager.set("111","111");
        System.out.println(redisManager.dbSize());
        redisManager.flushDb();
    }

}