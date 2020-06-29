//package com.ming.rabbitmqpdemo.config;
//
//import com.ming.rabbitmqpdemo.core.QueueHandler;
//import com.ming.rabbitmqpdemo.receive.IMark;
//import com.ming.rabbitmqpdemo.util.RabbitUtil;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.CommandLineRunner;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * 类作用描述
// *
// * @author zhangming
// * @version 1.0
// * @date 2020/6/17 10:25 上午
// */
//@Component
//public class MqListenerRunner implements CommandLineRunner {
//    @Autowired
//    private List<IMark> iMarks;
//    private RabbitUtil rabbitUtil;
//
//    @Autowired
//    public void setRabbitSendUtil(RabbitUtil rabbitSendUtil) {
//        this.rabbitUtil = rabbitSendUtil;
//    }
//
//    @Override
//    public void run(String... args) throws Exception {
//        iMarks.stream().forEach(map->{
//            QueueHandler queueHandler =  map.getQueueHandler();
//            rabbitUtil.createQueue(queueHandler);
//        });
//    }
//}
