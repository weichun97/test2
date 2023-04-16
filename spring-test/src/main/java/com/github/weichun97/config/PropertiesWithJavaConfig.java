package com.github.weichun97.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * springboot 不需要使用 @PropertySource，会自动读取 resources 目录下所有的配置文件
 */
@Data
@Configuration
@PropertySource("classpath:foo.properties")
public class PropertiesWithJavaConfig {

    @Value("${a}")
    private String a;
    @Value("${b}")
    private String b;
    @Value("${e}")
    private String e;
    @Value("${jdbcUrl:default}")
    private String jdbcUrl;
}
