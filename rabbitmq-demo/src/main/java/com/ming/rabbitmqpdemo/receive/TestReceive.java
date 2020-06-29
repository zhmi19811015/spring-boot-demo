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
 * @date 2019-12-06 23:05
 */
@Service
@Slf4j
public class TestReceive extends HandlerService implements IMark {
    @Override
    public Boolean receive(String routingKey, String message) {
        log.info("routingKey:" + routingKey + "。message：" + message);
        return true;
    }


    @Override
    public QueueHandler getQueueHandler() {
        return new QueueHandler("test12", true, false,
                "ming-routingkey-user",this);
    }

    @Override
    public void close() {

    }
}
