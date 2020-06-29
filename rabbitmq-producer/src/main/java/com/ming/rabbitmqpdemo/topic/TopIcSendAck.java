package com.ming.rabbitmqpdemo.topic;

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
import java.util.concurrent.atomic.AtomicInteger;

/**
 * ConfirmCallback 这种是用来确认生产者将消息发送给交换器，交换器传递给队列的过程中，
 * 消息是否成功投递。发送确认分为两步，一是确认是否到达交换器，二是确认是否到达队列
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/11 9:58 PM
 */
@Component
@Slf4j
public class TopIcSendAck implements RabbitTemplate.ConfirmCallback, RabbitTemplate.ReturnCallback {
    @Autowired
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
        int i = 0;
        while (true) {
            String str = String.valueOf(System.currentTimeMillis());
            CorrelationData correlationId = new CorrelationData(IdUtil.simpleUUID());
            //这里必须处理一个异常，否则连接不成功，程序退出
            try {
                rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-str", str, correlationId);
                log.info("发送成功。消息ID：" + correlationId.getId() + "。 消息内容:" + str);
                log.info("发送数量:"+i);
                i++;
            } catch (AmqpException e) {
                log.error("连接失败");
            }

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

    }

    public void sendUser() {
        User user = new User();
        CorrelationData correlationId = null;
        //int i = 0;
        String uuid = "";
        int c = count.incrementAndGet();
        int i = 1;
        while (i <= 20) {
            uuid = IdUtil.simpleUUID();
            correlationId = new CorrelationData(uuid);
            user.setId(IdUtil.simpleUUID());
            user.setUserName("zhangming" + c);
            user.setBirDate(DateUtil.formatDateTime(new Date()));
            user.setSex(i);
            //这里必须处理一个异常，否则连接不成功，程序退出
            try {
                rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-user", JSON.toJSONString(user), correlationId);
                log.info(Thread.currentThread().getName() + ",发送成功");
                Thread.sleep(1000);
            } catch (Exception e) {
                log.error("连接失败");
            }
            i++;
        }

    }

    /**
     * 功能描述: 设置优先级
     *
     * @author zhangming
     * @date 2019/5/15 1:51 PM
     */
    public void sendUserPriority() {
        User user = new User();
        CorrelationData correlationId = null;
        String uuid = "";
        int c = count.incrementAndGet();
        uuid = IdUtil.simpleUUID();
        correlationId = new CorrelationData(uuid);
        user.setId(IdUtil.simpleUUID());
        user.setUserName("zhangming" + c);
        user.setBirDate(DateUtil.formatDateTime(new Date()));
        user.setSex(1);

        //这里必须处理一个异常，否则连接不成功，程序退出
        try {
            rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-user", JSON.toJSONString(user), new MessagePostProcessor() {
                @Override
                public Message postProcessMessage(Message message) throws AmqpException {
                    int priority = 1;
                    if (c == 10 || c == 20 | c == 30) {
                        priority = 10;
                    }
                    message.getMessageProperties().setPriority(priority);
                    return message;
                }
            }, correlationId);
            log.info(Thread.currentThread().getId() + ",发送成功");
        } catch (AmqpException e) {
            log.error("连接失败");
        }
    }

    /**
     * 通过实现ConfirmCallBack接口，消息发送到交换器Exchange后触发回调。
     * 使用该功能需要开启确认，spring-boot中配置如下：
     * spring.rabbitmq.publisher-confirms = tru
     *
     * @param correlationData 1
     * @param ack 2
     * @param failMsg 3
     * @return  void
     * @author  zhangming
     * @date  2019-12-02 16:14
     */
    @Override
    public void confirm(@Nullable CorrelationData correlationData, boolean ack, @Nullable String failMsg) {
        log.info(" 回调id:" + correlationData.getId());
        if (ack) {
//            //测试重复发送
//            String str = String.valueOf(System.currentTimeMillis());
//            rabbitTemplate.convertAndSend("ming-exchange", "ming-routingkey-str", str, correlationData);
//            log.info("重复发送:" + msg);
        } else {
            log.info("消息发送失败:原因" + failMsg);
        }
    }

    /**
     * 通过实现ReturnCallback接口，如果消息从交换器发送到对应队列失败时触发（比如根据发送消息时指定的routingKey找不到队列时会触发）
     * 使用该功能需要开启确认，spring-boot中配置如下：
     *  spring.rabbitmq.publisher-returns = true
     * @param message 1
     * @param replyCode 2
     * @param replyText 3
     * @param exchange 4
     * @param routingKey 5
     * @return  void
     * @author  zhangming
     * @date  2019-12-02 16:13
     */
    @Override
    public void returnedMessage(Message message, int replyCode, String replyText,String exchange, String routingKey) {
        log.info("消息主题 message :"+message);
        log.info("replyCode:" + replyCode);
        log.info("描述:" + replyText);
        log.info("exchange:" + exchange);
        log.info("routingKey:" + routingKey);
    }
}
