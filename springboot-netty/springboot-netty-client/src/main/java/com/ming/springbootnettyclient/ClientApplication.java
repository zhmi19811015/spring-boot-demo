package com.ming.springbootnettyclient;

import com.ming.springbootnettyclient.netty.NettyClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class ClientApplication {

    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(ClientApplication.class, args);
        NettyClient nettyClient = context.getBean(NettyClient.class);
        nettyClient.run();
    }

}
