package com.fesco.saashr;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ServletComponentScan
@EnableTransactionManagement
@MapperScan("microservice.qssj.mapper")
public class SaashrAttendanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(SaashrAttendanceApplication.class, args);
    }
}
