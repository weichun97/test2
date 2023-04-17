package com.github.weichun97.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class DatabaseConfigTest {

    @Resource
    private DatabaseConfig databaseConfig;

    @Test
    public void test(){
        assertEquals("jdbc:postgresql:/localhost:5432/instance", databaseConfig.getUrl());
        assertEquals("foo", databaseConfig.getUsername());
        assertEquals("bar", databaseConfig.getPassword());
    }
}