package com.ming.springbootbase;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

//去掉springboot 默认的数据源配置
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class SpringbootBaseApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringbootBaseApplication.class, args);
	}

}
