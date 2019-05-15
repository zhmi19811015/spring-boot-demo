package com.ming.rabbitmqreceiver.topic;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/15 4:28 PM
 */
@Slf4j
@Service
public class TopicServer {



    @PostConstruct
    public void pollMsg() throws IOException {
//        ConnectionFactory factory = new ConnectionFactory();
//        //设置登录账号
//        factory.setHost(ServerInfo.host);
//        factory.setPort(ServerInfo.port);
//        factory.setUsername(ServerInfo.uName);
//        factory.setPassword(ServerInfo.uPwd);
//        //链接服务器
//        Connection connection = factory.newConnection();
//        Channel channel = connection.createChannel();


    }
}
