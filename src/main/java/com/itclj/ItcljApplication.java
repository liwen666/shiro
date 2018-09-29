package com.itclj;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.itclj.mapper")
@SpringBootApplication
public class ItcljApplication {
    public static void main(String[] args) {
        SpringApplication.run(ItcljApplication.class, args);
    }
}
