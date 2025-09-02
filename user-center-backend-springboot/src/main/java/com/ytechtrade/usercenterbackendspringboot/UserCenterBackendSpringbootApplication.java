package com.ytechtrade.usercenterbackendspringboot;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.ytechtrade.usercenterbackendspringboot.mapper")
public class UserCenterBackendSpringbootApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserCenterBackendSpringbootApplication.class, args);
    }

}
