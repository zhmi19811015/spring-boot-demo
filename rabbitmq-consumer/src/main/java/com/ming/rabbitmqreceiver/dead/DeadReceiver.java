package com.ming.rabbitmqreceiver.dead;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * 死信队列接受
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/13 9:13 AM
 */
@Component
@Slf4j
public class DeadReceiver {

    /**
     * 功能描述: 使用queues需要手动创建
     *
     * @param message 1
     * @param channel 2
     * @return  void
     * @author  zhangming
     * @date  2019/5/14 8:31 AM
     */
    @RabbitListener(queues = {"REDIRECT_QUEUE"})
    public void redirect(Message message, Channel channel) throws IOException {
        channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        log.info("dead message  30s 后 消费消息 {}",new String (message.getBody()));
    }
}
