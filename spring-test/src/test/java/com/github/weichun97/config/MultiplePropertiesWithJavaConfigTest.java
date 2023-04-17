package com.github.weichun97.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
public class MultiplePropertiesWithJavaConfigTest {

    @Resource
    private MultiplePropertiesWithJavaConfig multiplePropertiesWithJavaConfig;

    @Test
    public void test(){
        assertEquals("ccc", multiplePropertiesWithJavaConfig.getC());
        assertEquals("ddd", multiplePropertiesWithJavaConfig.getD());
    }
}