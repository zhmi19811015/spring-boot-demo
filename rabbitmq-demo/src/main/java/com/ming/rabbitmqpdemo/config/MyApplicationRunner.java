//package com.ming.rabbitmqpdemo.config;
//
//import com.ming.rabbitmqpdemo.receive.Receive;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
//import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.core.annotation.Order;
//import org.springframework.stereotype.Component;
//
///**
// * 类作用描述
// *
// * @author zhangming
// * @version 1.0
// * @date 2019-12-05 20:47
// */
//@Component
//@Order(10)
//public class MyApplicationRunner implements CommandLineRunner {
//    private final SimpleMessageListenerContainer container;
//    private final Receive receive;
//
//    @Autowired
//    public MyApplicationRunner(SimpleMessageListenerContainer container,Receive receive) {
//        this.container = container;
//        this.receive = receive;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        System.err.println("CommandLineRunner");
//        MessageListenerAdapter messageListenerAdapter = new MessageListenerAdapter(receive,new Jackson2JsonMessageConverter());
//        messageListenerAdapter.addQueueOrTagToMethodName("ming-routingkey-user-test","handleMessage");
//        messageListenerAdapter.addQueueOrTagToMethodName("ming-routingkey-str-test","process");
//        String[] strings = {"ming-routingkey-user-test","ming-routingkey-str-test"};
//        container.addQueueNames(strings);
//        container.setMessageListener(messageListenerAdapter);
//    }
//}
