//package com.ming.rabbitmqproduction.topic;
//
//
//import org.springframework.amqp.core.Exchange;
//import org.springframework.amqp.rabbit.connection.CorrelationData;
//import org.springframework.amqp.rabbit.core.RabbitTemplate;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Component;
//
///**
// * 类作用描述
// *
// * @author zhangming
// * @version 1.0
// * @date 2019-12-05 19:32
// */
//@Component
//public class TopicAck implements RabbitTemplate.ConfirmCallback {
//    @Autowired
//    private RabbitTemplate rabbitTemplate;
//    @Autowired
//    private Exchange mingTicpExchange;
//
//
//
//    @Override
//    public void confirm(CorrelationData correlationData, boolean b, String s) {
//
//    }
//}
