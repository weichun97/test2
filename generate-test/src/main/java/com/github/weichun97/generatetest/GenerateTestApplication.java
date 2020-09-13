package com.github.weichun97.generatetest;

import com.github.weichun97.generate.autoconfigure.annotations.EnableGenerate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableGenerate
@MapperScan(basePackages = "com.github.weichun97.generatetest.mapper")
@SpringBootApplication
public class GenerateTestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GenerateTestApplication.class, args);
	}

}
