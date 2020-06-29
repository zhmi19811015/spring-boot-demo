//package com.ming.rabbitmqpdemo.config;
//
//import org.springframework.amqp.core.AcknowledgeMode;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.core.RabbitAdmin;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.beans.factory.FactoryBean;
//
///**
// * 类作用描述
// *
// * @author zhangming
// * @version 1.0
// * @date 2019-12-09 16:43
// */
//public class DynamicConsumerContainerFactory implements FactoryBean<SimpleMessageListenerContainer> {
//
//    private ExchangeType exchangeType;
//
//    private String directExchange;
//    private String topicExchange;
//    private String fanoutExchange;
//
//    private String queue;
//    private String routingKey;
//
//
//    private Boolean autoDeleted;
//    private Boolean durable;
//    private Boolean autoAck;
//
//    private ConnectionFactory connectionFactory;
//    private RabbitAdmin rabbitAdmin;
//    private Integer concurrentNum;
//
//
//    // 消费方
//    private IMqConsumer consumer;
//
//    private Queue buildQueue() {
//        if (StringUtils.isEmpty(queue)) {
//            throw new IllegalArgumentException("queue name should not be null!");
//        }
//
//        return new Queue(queue, durable == null ? false : durable, false, autoDeleted == null ? true : autoDeleted);
//    }
//
//
//    private Binding bind(Queue queue, Exchange exchange) {
//        return exchangeType.binding(queue, exchange, routingKey);
//    }
//
//
//    private void check() {
//        if (null == rabbitAdmin || null == connectionFactory) {
//            throw new IllegalArgumentException("rabbitAdmin and connectionFactory should not be null!");
//        }
//    }
//    @Override
//    public SimpleMessageListenerContainer getObject() throws Exception {
//        Queue queue = buildQueue();
//        Exchange exchange = buildExchange();
//        Binding binding = bind(queue, exchange);
//
//        rabbitAdmin.declareQueue(queue);
//        rabbitAdmin.declareExchange(exchange);
//        rabbitAdmin.declareBinding(binding);
//
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer();
//        container.setRabbitAdmin(rabbitAdmin);
//        container.setConnectionFactory(connectionFactory);
//        container.setQueues(queue);
//        container.setPrefetchCount(20);
//        container.setConcurrentConsumers(concurrentNum == null ? 1 : concurrentNum);
//        container.setAcknowledgeMode(autoAck ? AcknowledgeMode.AUTO : AcknowledgeMode.MANUAL);
//
//
//        if (null != consumer) {
//            container.setMessageListener(consumer);
//        }
//        return container;
//    }
//
//    @Override
//    public Class<?> getObjectType() {
//        return SimpleMessageListenerContainer.class;
//    }
//}
