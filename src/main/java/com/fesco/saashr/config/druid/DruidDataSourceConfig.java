package com.fesco.saashr.config.druid;

import com.alibaba.druid.pool.DruidDataSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * @author: WangXingYu
 * @date: 2018-01-01
 */
@Configuration
@EnableConfigurationProperties(DruidSettings.class)
public class DruidDataSourceConfig {

    @Autowired
    private DruidSettings druidSettings;

    @Bean
    @ConfigurationProperties("spring.druid.datasource")
    public DruidDataSource dataSource() throws Exception {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDriverClassName(druidSettings.getDriverClassName());
        dataSource.setUrl(druidSettings.getUrl());
        dataSource.setUsername(druidSettings.getUsername());
        dataSource.setPassword(druidSettings.getPassword());
        dataSource.setInitialSize(druidSettings.getInitialSize());
        dataSource.setMinIdle(druidSettings.getMinIdle());
        dataSource.setMaxActive(druidSettings.getMaxActive());
        dataSource.setMaxWait(druidSettings.getMaxWait());
        dataSource.setTimeBetweenEvictionRunsMillis(druidSettings.getTimeBetweenEvictionRunsMillis());
        dataSource.setMinEvictableIdleTimeMillis(druidSettings.getMinEvictableIdleTimeMillis());
        String validationQuery = druidSettings.getValidationQuery();
        if (validationQuery != null && !"".equals(validationQuery)) {
            dataSource.setValidationQuery(validationQuery);
        }
        dataSource.setTestWhileIdle(druidSettings.isTestWhileIdle());
        dataSource.setTestOnBorrow(druidSettings.isTestOnBorrow());
        dataSource.setTestOnReturn(druidSettings.isTestOnReturn());
        if (druidSettings.isPoolPreparedStatements()) {
            dataSource.setMaxPoolPreparedStatementPerConnectionSize(druidSettings.getMaxPoolPreparedStatementPerConnectionSize());
        }

        //设置SQL监控
        dataSource.setFilters(druidSettings.getFilters());

        String connectionPropertiesStr = druidSettings.getConnectionProperties();
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
        dataSource.setUseGlobalDataSourceStat(druidSettings.isUseGlobalDataSourceStat());
        return dataSource;
    }
}