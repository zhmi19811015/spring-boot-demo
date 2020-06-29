package com.ming.springbootnettyserver;

import com.ming.springbootnettyserver.netty.NettyServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		ApplicationContext context = SpringApplication.run(ServerApplication.class, args);
		NettyServer nettyServer = context.getBean(NettyServer.class);
		nettyServer.run();
		//SpringApplication.run(ServerApplication.class, args);
	}

}
