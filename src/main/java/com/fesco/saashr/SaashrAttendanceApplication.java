package com.fesco.saashr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @author WangXingYu
 * @date 2018-01-01
 */
@SpringBootApplication
@MapperScan("com.fesco.saashr.web.dao")
@EnableTransactionManagement
@ServletComponentScan
@EnableSwagger2
public class SaashrAttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaashrAttendanceApplication.class, args);
    }
}