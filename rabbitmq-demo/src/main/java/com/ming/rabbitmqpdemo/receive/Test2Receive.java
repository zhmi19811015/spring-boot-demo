package com.ming.rabbitmqpdemo.receive;

import com.ming.rabbitmqpdemo.core.HandlerService;
import com.ming.rabbitmqpdemo.core.QueueHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-06 23:11
 */
@Service
@Slf4j
public class Test2Receive extends HandlerService implements IMark{
    private final static String QUEUE_NAME = "test1234";

    @Override
    public Boolean receive(String routingKey, String message) {
        log.info("routingKey:"+routingKey+"。message："+message);
        return true;
    }



    @Override
    public QueueHandler getQueueHandler() {
        return new QueueHandler(QUEUE_NAME,true, false,
                "ming-routingkey-user",this);
    }

    @Override
    public void close() {

    }
}
