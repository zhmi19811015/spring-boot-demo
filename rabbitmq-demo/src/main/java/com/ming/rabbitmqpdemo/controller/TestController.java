package com.ming.rabbitmqpdemo.controller;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSONObject;
import com.ming.rabbitmqpdemo.core.QueueHandler;
import com.ming.rabbitmqpdemo.pojo.User;
import com.ming.rabbitmqpdemo.receive.IMark;
import com.ming.rabbitmqpdemo.test.ITest;
import com.ming.rabbitmqpdemo.util.RabbitUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "test")
public class TestController {
    private RabbitUtil rabbitUtil;
    @Autowired
    private List<ITest> iTest;

    @Autowired
    private List<IMark> iMarks;



    @Autowired
    public void setRabbitSendUtil(RabbitUtil rabbitSendUtil) {
        this.rabbitUtil = rabbitSendUtil;
    }

    @GetMapping(value = "deleteQueue")
    public String deleteQueue(){
        rabbitUtil.delete230Queue();
        return "success";
    }

    @GetMapping(value = "test")
    public String test(){
        return "success";
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/sendRoutingKey")
    public void sendRoutingKey(){
        User user = new User();
        user.setBirDate(DateUtil.now());
        user.setId(IdUtil.simpleUUID());
        user.setSex(1);
        user.setUserName("andy");
        rabbitUtil.sendMessage("ming-routingkey-user", JSONObject.toJSONString(user));
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/send/QueueName/{message}")
    public void sendTest2(@PathVariable String message){
        rabbitUtil.sendToQueue("ming-routingkey-user-test",message);
    }

    @GetMapping("/createQueue")
    public String createQueue(String queueName){
//        rabbitUtil.createQueue(queueName+"_durable","ming-routingkey-user");
//        rabbitUtil.createQueueTemp(queueName+"_temp","ming-routingkey-user");

        iMarks.stream().forEach(map->{
            QueueHandler queueHandler =  map.getQueueHandler();
            rabbitUtil.createQueue(queueHandler);
        });

////    Channel channel = conn.createChannel(false);
////  channel.queueDeclare(queueName, true, false, false, null);
////    //声明交换机
////  channel.exchangeDeclare(rabbitmqUtil.exchangeName, "direct");
////    //绑定队列到交换机
////  channel.queueBind(queueName,rabbitmqUtil.exchangeName,"");
////    //将队列加入监听器
////    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitmqUtil.connectionFactory);
////  container.addQueueNames(queueName);

        return "success";
    }


//    private void bindQueue(QueueHandler queueHandler){
//        Queue queue = new Queue(queueHandler.name, queueHandler.durable, false, queueHandler.autoDelete);
//
//
//        rabbitAdmin.declareQueue(queue);
//        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
//        rabbitAdmin.declareBinding(binding);
//        //将队列加入监听器
//        //simpleMessageListenerContainer.addQueues(queue);
//    }

    @GetMapping("/itest")
    public String itest(){
        iTest.stream().forEach(map->{
            map.test();
        });
        return "success";
    }

//    @GetMapping("/test1")
//    public String test1(){
//        return  new Queue().test();
//    }


}
