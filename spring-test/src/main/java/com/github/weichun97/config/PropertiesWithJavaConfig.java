package com.github.weichun97.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:foo.properties")
public class PropertiesWithJavaConfig {

    @Value("${a}")
    private String a;
    @Value("${b}")
    private String b;
}
