package com.fesco.saashr.config.mybatis;

import com.fesco.saashr.config.druid.DruidConfig;
import com.github.pagehelper.PageHelper;
import org.springframework.boot.autoconfigure.AutoConfigureAfter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author: WangXingYu
 * @date: 2018-01-01
 */
@Configuration
@AutoConfigureAfter(DruidConfig.class)
public class MybatisConfig {
    @Bean
    public PageHelper pageHelper() {
        PageHelper pageHelper = new PageHelper();
        Properties p = new Properties();
        p.setProperty("offsetAsPageNum", "true");
        p.setProperty("rowBoundsWithCount", "true");
        p.setProperty("reasonable", "true");
        pageHelper.setProperties(p);
        return pageHelper;
    }
}