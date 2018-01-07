package com.fesco.saashr.web.util;

import com.fesco.saashr.web.common.RedisComponent;
import com.fesco.saashr.web.model.User;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author WangXingYu
 * @date 2018-01-07
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class TestRedis {
    @Autowired
    private RedisComponent redisComponent;

    @Test
    public void general() {
        redisComponent.set("key", "value");

        Assert.assertEquals(redisComponent.get("key"), "value");

        redisComponent.del("key");
    }

    @Test
    public void list() {
        for (int i = 0; i < 10; i++) {
            User user = new User();
            user.setUsername("list" + i);
            redisComponent.lSet("list", user);
        }

        List<User> results = redisComponent.lGet("list", 0, -1);

        for (int i = 0; i < results.size(); i++) {
            Assert.assertEquals(results.get(i).getUsername(), "list" + i);
        }

        User user = new User();
        user.setUsername("list" + 666);
        redisComponent.lUpdateIndex("list", 2, user);

        Assert.assertEquals(((User) redisComponent.lGetIndex("list", 2)).getUsername(), "list" + 666);

        redisComponent.del("list");
    }

    @Test
    public void map() {
        Map<String, Object> map = new HashMap<>();

        map.put("userName", 666);

        redisComponent.hmset("map", map);

        Map<String, Object> result = redisComponent.hmget("map");

        Assert.assertEquals(result.get("userName"), 666);

        redisComponent.del("map");
    }
}
