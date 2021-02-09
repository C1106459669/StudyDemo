package com.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan("com.test.mapper")
@SpringBootApplication
public class TestFastdfsApplication {

    public static void main(String[] args) {
        SpringApplication.run(TestFastdfsApplication.class,args);
    }

}
