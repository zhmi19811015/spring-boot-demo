package com.ming.rabbitmqreceiver.topic;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/13 9:10 PM
 */
@Slf4j
@Component
public class ReceiverTopic {


    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value="ming-topic-queue"),exchange =@Exchange(value = "ming-exchange",type = "topic"),key = "ming-routingkey")})
    public void receiver(String message){
        log.info("Thread:"+Thread.currentThread().getId()+"。receiver:"+ DateUtil.formatDateTime(new Date())+"。内容："+message+"");
    }

    /**
     * 功能描述: 使用bindings无需手动创建
     *
     * @param message 1
     * @return  void
     * @author  zhangming
     * @date  2019/5/14 8:30 AM
     */
//    @RabbitListener(bindings = {@QueueBinding(value = @Queue(value="ming-topic-queue-user"),exchange =@Exchange(value = "ming-exchange",type = "topic"),key = "ming-routingkey-user")})
//    public void receiverUser(@Payload String message, @Header(AmqpHeaders.DELIVERY_TAG) long deliveryTag, Channel channel) throws InterruptedException, IOException {
//        User user = JSON.parseObject(message,User.class);
//        channel.basicAck(deliveryTag,false);
//        log.info("Thread:"+Thread.currentThread().getId()+"。receiverUser:"+ DateUtil.formatDateTime(new Date())+"。内容："+user.getBirDate()+"");
//        Thread.sleep(1000);
//    }

    //@RabbitListener(bindings = {@QueueBinding(value = @Queue(value="ming-topic-queue-user"),exchange =@Exchange(value = "ming-exchange",type = "topic"),key = "ming-routingkey-user")})
    @RabbitListener(queues = {"ming-topic-queue-user"})
    public void receiverUser(Message message, Channel channel) throws InterruptedException, IOException {
        String msg = new String (message.getBody());
        User user = JSON.parseObject(msg,User.class);
        //业务处理完毕，执行ack
        channel.basicAck(message.getMessageProperties().getDeliveryTag(),false);
        log.info("Thread:"+Thread.currentThread().getId()+"。receiverUser:"+ DateUtil.formatDateTime(new Date())+"。内容："+user.getBirDate()+"");
        Thread.sleep(1000);
//
//        //一次拉取消息
//        GetResponse response = channel.basicGet("ming-topic-queue-user",false);
//        System.out.println(new String(response.getBody()));
//        channel.basicAck(response.getEnvelope().getDeliveryTag(),false);
    }

}
