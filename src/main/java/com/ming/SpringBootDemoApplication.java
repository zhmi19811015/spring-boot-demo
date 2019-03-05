package com.ming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SpringBootDemoApplication {

	public static void main(String[] args) {
		System.out.println("SpringBootApplication start");
		SpringApplication.run(SpringBootDemoApplication.class, args);
		System.out.println("SpringBootApplication end");
	}

}
