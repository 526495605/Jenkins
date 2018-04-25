package com.example.redistest;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

@ServletComponentScan
@SpringBootApplication
@MapperScan("com.example.redistest")
public class RedistestApplication {

	public static void main(String[] args) {
		SpringApplication.run(RedistestApplication.class, args);
	}
}
