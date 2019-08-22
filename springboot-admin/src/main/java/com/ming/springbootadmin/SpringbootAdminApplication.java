package com.ming.springbootadmin;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class SpringbootAdminApplication {

	public static void main(String[] args) {

		SpringApplication.run(SpringbootAdminApplication.class, args);
		//Tomcat tomcat = new Tomcat();
		//tomcat.setPort();
	}

}
