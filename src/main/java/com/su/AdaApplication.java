package com.su;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
@SpringBootApplication
@MapperScan("com.su.mapper")
public class AdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdaApplication.class, args);
    }
}
