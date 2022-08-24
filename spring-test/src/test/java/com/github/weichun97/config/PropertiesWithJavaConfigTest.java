package com.github.weichun97.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class PropertiesWithJavaConfigTest {

    @Resource
    private PropertiesWithJavaConfig propertiesWithJavaConfig;

    @Test
    public void test(){
        String a = propertiesWithJavaConfig.getA();
        assertEquals("1", a);
    }
}