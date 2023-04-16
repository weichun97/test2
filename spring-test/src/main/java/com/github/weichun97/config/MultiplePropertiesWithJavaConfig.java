package com.github.weichun97.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Data
@Configuration
@PropertySource("classpath:foo.properties")
@PropertySource("classpath:bar.properties")
public class MultiplePropertiesWithJavaConfig {

    @Value("${c}")
    private String c;
    @Value("${d}")
    private String d;
}
