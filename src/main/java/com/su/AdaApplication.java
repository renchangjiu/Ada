package com.su;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * @author su
 * @date 2019/9/18 8:44
 */
@EnableAsync
@SpringBootApplication
@MapperScan("com.su.mapper")
public class AdaApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdaApplication.class, args);
    }
}
