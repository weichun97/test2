package com.github.weichun97.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "database")
public class DatabaseConfig {
    String url;
    String username;
    String password;
}