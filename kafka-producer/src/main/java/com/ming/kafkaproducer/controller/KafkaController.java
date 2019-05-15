package com.ming.kafkaproducer.controller;

import com.alibaba.fastjson.JSON;
import com.ming.kafkaproducer.dto.User;
import com.ming.kafkaproducer.hanler.KafkaSendResultHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutionException;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/14 3:37 PM
 */
@RestController
@RequestMapping("kafka")
@Slf4j
public class KafkaController {
    @Autowired
    private KafkaTemplate<String, String> kafkaTemplate;
    @Autowired
    private KafkaSendResultHandler producerListener;

    @PostMapping("/send")
    public String send(String msg){
        kafkaTemplate.send("ming-topic",msg);
        log.info("发送成功");
        return msg;
    }

    @PostMapping("/sendUser")
    public String sendUser(User user){
        //回调监听
        kafkaTemplate.setProducerListener(producerListener);

        kafkaTemplate.send("ming-topic", JSON.toJSONString(user));
        log.info("发送成功");
        return user.getId();
    }

    /**
     * 功能描述: 同步发送。kafka默认是异步
     *
     * @param user 1
     * @return  java.lang.String
     * @author  zhangming
     * @date  2019/5/14 5:59 PM
     */
    @PostMapping("/sendSyncUser")
    public String sendSyncUser(User user) throws ExecutionException, InterruptedException {
        //回调监听
        kafkaTemplate.setProducerListener(producerListener);

        kafkaTemplate.send("ming-topic", JSON.toJSONString(user)).get();
        log.info("发送成功");
        return user.getId();
    }
}
