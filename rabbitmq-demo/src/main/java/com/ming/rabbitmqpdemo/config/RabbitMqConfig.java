package com.ming.rabbitmqpdemo.config;

import org.springframework.amqp.core.AcknowledgeMode;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-05 20:22
 */
@Configuration
public class RabbitMqConfig {
//    @Value("${spring.rabbitmq.host}")
//    private String host;
//    @Value("${spring.rabbitmq.port}")
//    private int port;
//    @Value("${spring.rabbitmq.username}")
//    private String uname;
//    @Value("${spring.rabbitmq.password}")
//    private String passwd;

    @Value("${spring.rabbitmq.ex-change-name}")
    private String exchangeName;

//    @Bean
//    public ConnectionFactory connectionFactory(){
//        CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory();
//        cachingConnectionFactory.setUsername(uname);
//        cachingConnectionFactory.setPassword(passwd);
//        cachingConnectionFactory.setVirtualHost("/");
//        cachingConnectionFactory.setHost(host);
//        cachingConnectionFactory.setPort(port);
//        return cachingConnectionFactory;
//    }

    /**
     * 创建admin 为了自定义队列或交换机
     *
     * @param connectionFactory 1
     * @return org.springframework.amqp.rabbit.core.RabbitAdmin
     * @author zhangming
     * @date 2019-12-05 20:28
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) {
        return new RabbitAdmin(connectionFactory);
    }

    /**
     * 功能描述:
     *
     * @param connectionFactory 1
     * @return org.springframework.amqp.rabbit.core.RabbitTemplate
     * @author zhangming
     * @date 2019-12-05 20:29
     */
    @Bean
    public RabbitTemplate rabbitTemplate(ConnectionFactory connectionFactory) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        //数据转换为json存入消息队列
        rabbitTemplate.setMessageConverter(new Jackson2JsonMessageConverter());
        return rabbitTemplate;
    }

    @Bean
    public TopicExchange exchange(RabbitAdmin rabbitAdmin) {
        TopicExchange dataExchange = new TopicExchange(exchangeName, true, false);
        rabbitAdmin.declareExchange(dataExchange);
        //删除Exchange:
        //rabbitAdmin.deleteExchange(exchangeName);
        //创建Queue:
        //Queue queue = new Queue(queueName,true,false,false);
        // rabbitAdmin.declareQueue(queue);
        //删除Queue：
        //rabbitAdmin.deleteQueue(queueName);
        //建立绑定(这里给出Topic的匹配绑定)：
        //Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey+".#");
        //rabbitAdmin.declareBinding(binding);

        return dataExchange;
    }



    /**
     * 创建监听器，监听队列
     *
     * @param connectionFactory 1
     * @return org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer
     * @author zhangming
     * @date 2019-12-05 20:45
     */
    @Bean
    public SimpleMessageListenerContainer container(ConnectionFactory connectionFactory) {
        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(connectionFactory);
        container.setExposeListenerChannel(true);
//        container.setPrefetchCount(12);//设置每个消费者获取的最大的消息数量
//        container.setConcurrentConsumers(12);//消费者个数
        //设置确认模式为手工确认
        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
        //监听处理类
        //container.setMessageListener(handlerService);


//            Connection conn =connectionFactory.createConnection();
//    Channel channel = conn.createChannel(false);
//  channel.queueDeclare(queueName, true, false, false, null);
//    //声明交换机
//  channel.exchangeDeclare(rabbitmqUtil.exchangeName, "direct");
//    //绑定队列到交换机
//  channel.queueBind(queueName,rabbitmqUtil.exchangeName,"");
//    //将队列加入监听器
//    SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitmqUtil.connectionFactory);
//  container.addQueueNames(queueName);

        return container;
    }


}
