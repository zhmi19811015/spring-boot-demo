package com.ming.rabbitmqproduction.topic;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/11 9:58 PM
 */
@Component
@Slf4j
public class TopIcSendAck implements RabbitTemplate.ConfirmCallback,RabbitTemplate.ReturnCallback {
    private RabbitTemplate rabbitTemplate;
    private AtomicInteger count = new AtomicInteger();

    /**
     * 构造方法注入
     */
    @Autowired
    public TopIcSendAck(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        //rabbitTemplate如果为单例的话，那回调就是最后设置的内容
        rabbitTemplate.setConfirmCallback(this);
    }

    public void sendMsg() {
        while (true){
            String str = String.valueOf(System.currentTimeMillis());
            CorrelationData correlationId = new CorrelationData(UUID.randomUUID().toString());
            //这里必须处理一个异常，否则连接不成功，程序退出
            try{
                rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-str", str, correlationId);
            }catch (AmqpException e){
                log.error("连接失败");
            }

            try {
                Thread.sleep(200);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendUser(){
        User user = new User();
        CorrelationData correlationId = null;
        //int i = 0;
        String uuid = "";
        int c = count.incrementAndGet();
        if (c <= 1000){
            uuid = IdUtil.simpleUUID();
            correlationId = new CorrelationData(uuid);
            user.setId(IdUtil.simpleUUID());
            user.setUserName("zhangming"+c);
            user.setBirDate(DateUtil.formatDateTime(new Date()));
            user.setSex(1);
            //这里必须处理一个异常，否则连接不成功，程序退出
            try{
                rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-user", JSON.toJSONString(user), correlationId);
                log.info(Thread.currentThread().getId()+",发送成功");
            }catch (AmqpException e){
                log.error("连接失败");
            }
        }else{
            log.error("次数到达限制");
        }
    }

    /**
     * 功能描述: 设置优先级
     *
     * @author  zhangming
     * @date  2019/5/15 1:51 PM
     */
    public void sendUserPriority(){
        User user = new User();
        CorrelationData correlationId = null;
        String uuid = "";
        int c = count.incrementAndGet();
        uuid = IdUtil.simpleUUID();
        correlationId = new CorrelationData(uuid);
        user.setId(IdUtil.simpleUUID());
        user.setUserName("zhangming"+c);
        user.setBirDate(DateUtil.formatDateTime(new Date()));
        user.setSex(1);

        //这里必须处理一个异常，否则连接不成功，程序退出
        try{
            rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-user", JSON.toJSONString(user), new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    int priority = 1;
                    if (c==10 || c== 20| c==30){
                        priority = 10;
                    }
                    message.getMessageProperties().setPriority(priority);
                    return message;
                }
            },correlationId);
            log.info(Thread.currentThread().getId()+",发送成功");
        }catch (AmqpException e){
            log.error("连接失败");
        }
    }

    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String msg) {
        log.info(" 回调id:" + correlationData);
        if (ack) {
            log.info("消息成功:"+msg);
        } else {
            log.info("消息发送失败:" + msg);
        }
    }

    @Override
    public void returnedMessage(Message message, int i, String s, String s1, String s2) {
        log.info("returnedMessage");
        log.info("i:"+i);
        log.info("s:"+s);
        log.info("s1:"+s1);
        log.info("s2:"+s2);
    }
}
