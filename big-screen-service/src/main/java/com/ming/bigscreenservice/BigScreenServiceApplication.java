package com.ming.bigscreenservice;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableScheduling;

@ComponentScan("com.ming")
@SpringBootApplication
@EnableScheduling
@MapperScan("com.ming.bigscreenservice.mapper")
public class BigScreenServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(BigScreenServiceApplication.class, args);
	}

}
