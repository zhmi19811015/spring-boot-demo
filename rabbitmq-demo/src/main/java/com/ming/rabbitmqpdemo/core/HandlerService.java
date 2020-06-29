package com.ming.rabbitmqpdemo.core;

import com.rabbitmq.client.Channel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;

/**
 * 类作用描述ChannelAwareMessageListener
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-06 12:11
 */
//@Component
@Slf4j
public abstract class HandlerService extends MessageListenerAdapter  {

    /**
     * 1、处理成功，这种时候用basicAck确认消息；
     * 2、可重试的处理失败，这时候用basicNack将消息重新入列；
     * 3、不可重试的处理失败，这时候使用basicNack将消息丢弃。
     * <p>
     *   basicNack(long deliveryTag, boolean multiple, boolean requeue)
     *    deliveryTag:该消息的index
     *   multiple：是否批量.true:将一次性拒绝所有小于deliveryTag的消息。
     * requeue：被拒绝的是否重新入队列
     *
     * @param message 1
     * @param channel 2
     * @return void
     * @author zhangming
     * @date 2019-12-06 12:13
     */
    @Override
    public void onMessage(Message message, Channel channel) throws Exception {

        String strMsg = new String(message.getBody(), "UTF-8");
        log.info("接收到消息:" + strMsg);
        String routingKey = message.getMessageProperties().getReceivedRoutingKey();
        String queueName = message.getMessageProperties().getConsumerQueue();
        try {
            boolean isSuccess = receive(routingKey, strMsg);
            if (isSuccess) {
                //确认消息消费成功 
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
            } else {
                channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, true);
            }

        } catch (Exception e) {
            //消息丢弃
            channel.basicNack(message.getMessageProperties().getDeliveryTag(), false, false);
            log.error("routingKey处理失败:" + routingKey, e);
        }

    }

    public abstract Boolean receive(String routingKey,String message);

}
