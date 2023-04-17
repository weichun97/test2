package com.github.weichun97.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.env.Environment;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PropertiesWithJavaConfigTest {

    @Resource
    private PropertiesWithJavaConfig propertiesWithJavaConfig;
    @Resource
    private Environment environment;

    @Test
    public void test(){
        assertEquals("123", propertiesWithJavaConfig.getA());
        assertEquals("456", propertiesWithJavaConfig.getB());
        assertEquals("eee", propertiesWithJavaConfig.getE());
    }

    @Test
    public void testDefault(){
        assertEquals("default", propertiesWithJavaConfig.getJdbcUrl());
    }

    @Test
    public void testEnvironment(){
        assertEquals("123", environment.getProperty("a"));
    }
}