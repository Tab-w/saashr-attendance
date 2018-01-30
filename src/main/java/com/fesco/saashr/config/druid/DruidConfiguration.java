package com.fesco.saashr.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

import java.util.Properties;

/**
 * @author: WangXingYu
 * @date: 2018-01-01
 */
@Configuration
public class DruidConfiguration {

    private final Environment environment;

    @Autowired
    public DruidConfiguration(Environment environment) {
        this.environment = environment;
    }

    @Bean
    public DruidDataSource dataSource() throws Exception {

        DruidDataSource dataSource = new DruidDataSource();

        //驱动类
        dataSource.setDriverClassName(environment.getProperty("spring.datasource.driver-class-name"));

        //基本属性 url、username、password
        dataSource.setUrl(environment.getProperty("spring.datasource.url"));
        dataSource.setUsername(environment.getProperty("spring.datasource.username"));
        dataSource.setPassword(environment.getProperty("spring.datasource.password"));

        //连接池初始化大小、最小和最大值
        dataSource.setInitialSize(environment.getProperty("spring.datasource.initialSize", Integer.class));
        dataSource.setMinIdle(environment.getProperty("spring.datasource.minIdle", Integer.class));
        dataSource.setMaxActive(environment.getProperty("spring.datasource.maxActive", Integer.class));

        //获取连接等待超时的时间
        dataSource.setMaxWait(environment.getProperty("spring.datasource.maxWait", Long.class));
        //间隔多久才进行一次检测，检测需要关闭的空闲连接
        dataSource.setTimeBetweenEvictionRunsMillis(environment.getProperty("spring.datasource.timeBetweenEvictionRunsMillis", Long.class));
        //连接在池中最小生存的时间
        dataSource.setMinEvictableIdleTimeMillis(environment.getProperty("spring.datasource.minEvictableIdleTimeMillis", Long.class));

        String validationQuery = environment.getProperty("spring.datasource.validationQuery");
        if (validationQuery != null && !"".equals(validationQuery)) {
            dataSource.setValidationQuery(validationQuery);
        }
        dataSource.setTestWhileIdle(environment.getProperty("spring.datasource.testWhileIdle", Boolean.class));
        dataSource.setTestOnBorrow(environment.getProperty("spring.datasource.testOnBorrow", Boolean.class));
        dataSource.setTestOnReturn(environment.getProperty("spring.datasource.testOnReturn", Boolean.class));

        boolean opened = environment.getProperty("spring.datasource.poolPreparedStatements", Boolean.class);
        if (opened) {
            //打开PSCache，并且指定每个连接上PSCache的大小
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(environment.getProperty("spring.datasource.maxPoolPreparedStatementPerConnectionSize", Integer.class));
        }

        //监控统计拦截的filters
        dataSource.setFilters(environment.getProperty("spring.datasource.filters"));

        //通过connectProperties属性来打开mergeSql功能，慢SQL记录
        String connectionPropertiesStr = environment.getProperty("spring.datasource.connectionProperties");
        if (connectionPropertiesStr != null && !"".equals(connectionPropertiesStr)) {
            Properties connectProperties = new Properties();
            String[] propertiesList = connectionPropertiesStr.split(";");
            for (String propertiesTmp : propertiesList) {
                String[] obj = propertiesTmp.split("=");
                String key = obj[0];
                String value = obj[1];
                connectProperties.put(key, value);
            }
            dataSource.setConnectProperties(connectProperties);
        }
        //合并多个DruidDataSource的监控数据
        dataSource.setUseGlobalDataSourceStat(environment.getProperty("spring.datasource.useGlobalDataSourceStat", Boolean.class));
        return dataSource;
    }
}