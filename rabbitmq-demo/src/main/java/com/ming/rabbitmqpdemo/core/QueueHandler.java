package com.ming.rabbitmqpdemo.core;

/**
 * Queue-hanler类
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-06 11:01
 */
public class QueueHandler {
    public String name;
    /**
     * 队列是否持久化 。如果临时队列，这里用false.
     */
    public Boolean durable;
    /**
     * 是否自动删除 如果是临时队列，这里true.
     */
    public Boolean autoDelete;
    /**
     * 绑定的routingKey .
     */
    public String[] routingKeys;
    /**
     * 接收消息IReceiver实现类 .
     */
    public IReceiver receiver;
    public HandlerService handlerService;

    public QueueHandler(String name, String routingKey, IReceiver receiver) {
        this(name, true, false, routingKey, receiver);
    }

    public QueueHandler(String name, Boolean durable, Boolean autoDelete, String routingKey,HandlerService handlerService) {
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.routingKeys = new String[]{routingKey};
        this.handlerService = handlerService;
    }


    public QueueHandler(String name, Boolean durable, Boolean autoDelete, String routingKey, IReceiver receiver) {
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.routingKeys = new String[]{routingKey};
        this.receiver = receiver;
    }

    public QueueHandler(String name, Boolean durable, Boolean autoDelete, String[] routingKeys, IReceiver receiver) {
        this.name = name;
        this.durable = durable;
        this.autoDelete = autoDelete;
        this.routingKeys = routingKeys;
        this.receiver = receiver;
    }
}
