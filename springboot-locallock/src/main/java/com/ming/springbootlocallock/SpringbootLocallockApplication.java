package com.ming.springbootlocallock;

import com.ming.springbootlocallock.interceptor.CacheKeyGenerator;
import com.ming.springbootlocallock.interceptor.LockKeyGenerator;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SpringbootLocallockApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootLocallockApplication.class, args);
	}

	@Bean
	public CacheKeyGenerator cacheKeyGenerator() {
		return new LockKeyGenerator();
	}
}
