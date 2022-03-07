package com.github.weichun97.mysqlreplicationtest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = "com.github.weichun97.mysqlreplicationtest.entity.mapper")
@SpringBootApplication
public class MysqlReplicationTestApplication {

    public static void main(String[] args) {
        SpringApplication.run(MysqlReplicationTestApplication.class, args);
    }

}
