package com.ming.rabbitmqpdemo.core;

/**
 * 消息接收处理接口
 * FunctionalInterface注解：
 * 1、接口有且仅有一个抽象方法
 * 2、允许定义静态方法
 * 3、允许定义默认方法
 * 4、允许java.lang.Object中的public方法
 * @author zhangming
 * @version 1.0
 * @date 2019-12-06 11:03
 */
@FunctionalInterface
public interface IReceiver {
    /**
     * 订阅接收MQ发送的消息
     * @param routingKey 队列关键字
     * @param message    消息
     * @return 是否确认收到消息, true:是;false:否;
     */
    Boolean receive(String routingKey, String message);
}
