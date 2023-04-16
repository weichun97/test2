package com.github.weichun97.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AnnotationConfig {

    @Bean(name = {"tiger", "kitty"})
    @Scope(value = "prototype")
    Tiger getTiger(String name) {
        return new Tiger(name);
    }

    @Bean(name = "lion")
    Lion getLion() {
        return new Lion("Hardcoded lion name");
    }

    public interface Animal {}

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Lion{
        private String name;
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Tiger{
        private String name;
    }
}