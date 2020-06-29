package com.ming.rabbitmqpdemo.util;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.IdUtil;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ming.rabbitmqpdemo.HttpUtil;
import com.ming.rabbitmqpdemo.core.HandlerService;
import com.ming.rabbitmqpdemo.core.QueueHandler;
import com.rabbitmq.client.Channel;
import io.micrometer.core.instrument.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-05 20:35
 */
@Component
@Slf4j
public class RabbitUtil implements RabbitTemplate.ConfirmCallback {

    private RabbitTemplate rabbitTemplate;
    private TopicExchange topicExchange;
    private SimpleMessageListenerContainer simpleMessageListenerContainer;
    private RabbitAdmin rabbitAdmin;
    private List<SimpleMessageListenerContainer> listenerContainerList ;


    @Autowired
    public void setRabbitAdmin(RabbitAdmin rabbitAdmin) {
        this.rabbitAdmin = rabbitAdmin;
    }

    @Autowired
    public void setRabbitTemplate(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
        rabbitTemplate.setConfirmCallback(this);
    }

    @Autowired
    public void setTopicExchange(TopicExchange topicExchange) {
        this.topicExchange = topicExchange;
    }

    @Autowired
    public void setSimpleMessageListenerContainer(SimpleMessageListenerContainer simpleMessageListenerContainer) {
        this.simpleMessageListenerContainer = simpleMessageListenerContainer;
    }

    @Autowired
    public void setListenerContainerList(List<SimpleMessageListenerContainer> listenerContainerList)  {
        this.listenerContainerList = listenerContainerList;
    }

    /**
     * 有绑定Key的Exchange发送
     *
     * @param routingKey
     * @param msg
     */
    public void sendMessage(String routingKey, Object msg) {
        CorrelationData correlationId = new CorrelationData(IdUtil.simpleUUID());
        rabbitTemplate.setExchange(topicExchange.getName());
        //rabbitTemplate.setRoutingKey(routingKey);
        //send方法复杂不会序列化
        rabbitTemplate.convertAndSend(routingKey, msg, correlationId);
    }

    /**
     * 没有绑定KEY的Exchange发送
     *
     * @param exchange
     * @param msg
     */
    public void sendMessage(AbstractExchange exchange, String msg) {
        rabbitAdmin.declareExchange(exchange);
        rabbitTemplate.setExchange(exchange.getName());
        log.info("RabbitMQ send " + exchange.getName() + "->" + msg);
        rabbitTemplate.send(new Message(msg.getBytes(), new MessageProperties()));
    }

    /**
     * 给queue发送消息
     *
     * @param queueName
     * @param msg
     */
    @Async
    public void sendToQueue(String queueName, String msg) {
        Queue queue = new Queue(queueName);
        this.addQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(DirectExchange.DEFAULT).withQueueName();
        rabbitAdmin.declareBinding(binding);
        MessageProperties messageProperties = new MessageProperties();
        //设置消息内容的类型，默认是 application/octet-stream 会是 ASCII 码值
//		messageProperties.setContentType(MessageProperties.CONTENT_TYPE_TEXT_PLAIN);
        messageProperties.setContentType(MessageProperties.CONTENT_TYPE_JSON);
        Message message = new Message(msg.getBytes(), messageProperties);
        rabbitTemplate.convertAndSend(DirectExchange.DEFAULT.getName(), queueName, message);




    }

    /**
     * 创建Exchange
     *
     * @param exchange
     */
    private void addExchange(AbstractExchange exchange) {
        rabbitAdmin.declareExchange(exchange);
    }

    /**
     * 删除一个Exchange
     *
     * @param exchangeName
     */
    public boolean deleteExchange(String exchangeName) {
        return rabbitAdmin.deleteExchange(exchangeName);
    }


    /**
     * Declare a queue whose name is automatically named. It is created with exclusive = true, autoDelete=true, and
     * durable = false.
     *
     * @return Queue
     */
    public Queue addQueue() {
        return rabbitAdmin.declareQueue();
    }

    /**
     * 创建一个指定的Queue
     *
     * @param queue
     * @return queueName
     */
    private String addQueue(Queue queue) {
        Queue queue1 = new Queue("queue.name", true);
        return rabbitAdmin.declareQueue(queue);
    }

    /**
     * Delete a queue.
     *
     * @param queueName the name of the queue.
     * @param unused    true if the queue should be deleted only if not in use.
     * @param empty     true if the queue should be deleted only if empty.
     */
    public void deleteQueue(String queueName, boolean unused, boolean empty) {
        rabbitAdmin.deleteQueue(queueName, unused, empty);
    }

    public void delete230Queue(){
        try{
            String result = HttpUtil.getApiMessage();
            if(StringUtils.isNotBlank(result)){
                JSONArray jsonArray = JSON.parseArray(result);
                for(int i = 0; i < jsonArray.size(); i++){
                    JSONObject jsonObject = JSONObject.parseObject(jsonArray.getString(i));
                    String name = jsonObject.getString("name");

                    if (name.startsWith("its_signal_run_status_1")){
                        deleteQueue(name);
                        System.out.println("删除队列："+name);
                    }

                }
            }
        }catch (Exception e){

        }

    }

    /**
     * 删除一个queue
     *
     * @param queueName
     * @return true if the queue existed and was deleted.
     */
    public boolean deleteQueue(String queueName) {
        return rabbitAdmin.deleteQueue(queueName);
    }

//    /**
//     * 绑定一个队列到一个匹配型交换器使用一个routingKey
//     * @param queue
//     * @param exchange
//     * @param routingKey
//     */
//    public void addBinding(Queue queue ,TopicExchange exchange,String routingKey){
//        Binding binding = BindingBuilder.bind(queue).to(exchange).with(routingKey);
//        rabbitAdmin.declareBinding(binding);
//    }


    /**
     * 创建一个队列（永久队列）
     *
     * @param queueName  队列名称
     * @param routingKey 绑定routingKey
     * @return void
     * @author zhangming
     * @date 2019-12-06 09:52
     */
    public void createQueue(String queueName, String routingKey, HandlerService handlerService) {
        createQueueAndBind(queueName, routingKey, true, handlerService);
    }

    public void createQueue(QueueHandler queueHandler) {
        Console.log(queueHandler.handlerService.getClass().getName());
        createQueueAndBind(queueHandler.name, queueHandler.routingKeys[0], true, queueHandler.handlerService);
    }

    /**
     * 创建一个队列（临时队列）
     * 临时队列与channel一同失效
     *
     * @param queueName  队列名称
     * @param routingKey 绑定routingKey
     * @return void
     * @author zhangming
     * @date 2019-12-06 09:52
     */
    public void createQueueTemp(String queueName, String routingKey, HandlerService handlerService) {

        createQueueAndBind(queueName, routingKey, false, handlerService);
    }

    /**
     * 创建一个队列
     *
     * @param queueName  队列名称
     * @param routingKey 绑定routingKey
     * @param durable    是否是持久队列
     * @return void
     * @author zhangming
     * @date 2019-12-06 09:52
     */
    private void createQueueAndBind(String queueName, String routingKey, boolean durable, HandlerService handlerService) {
        boolean isAutoDelete = false;
        if (!durable) {
            isAutoDelete = true;
        }
        //exclusive是否声明排他型队列，意思：如果你想创建一个只有自己可见的队列，即不允许其它用户访问
        Queue queue = new Queue(queueName, durable, false, isAutoDelete);

        rabbitAdmin.declareQueue(queue);
        Binding binding = BindingBuilder.bind(queue).to(topicExchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);

        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
        //将队列加入监听器
        simpleMessageListenerContainer.setMessageListener(handlerService);
        simpleMessageListenerContainer.addQueues(queue);

//        Channel channel = rabbitTemplate.getConnectionFactory().createConnection().createChannel(false);
//        try {
//            channel.queueDeclare(queueName,true,false,false,null);
//            //channel.exchangeDeclare(topicExchange.getName(),topicExchange.getType());
//            channel.queueBind(queueName,topicExchange.getName(),routingKey);
//            SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
//            container.addQueueNames(queueName);
//            container.setMessageListener(handlerService);
//            //后置处理器，接收到的消息都添加了Header请求头
//            container.setAfterReceivePostProcessors(message -> {
//                message.getMessageProperties().getHeaders().put("desc",10);
//               return message;
//            });
//
//
//
//            //设置消费者的consumerTag_tag
//            container.setConsumerTagStrategy(queue -> "order_queue_1");
//            //设置消费者的Arguments
//            Map<String, Object> args = new HashMap<>();
//            args.put("module","订单模块");
//            args.put("fun","发送消息");
//            container.setConsumerArguments(args);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


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

//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(rabbitTemplate.getConnectionFactory());
//        container.setExposeListenerChannel(true);
////        container.setPrefetchCount(12);//设置每个消费者获取的最大的消息数量
////        container.setConcurrentConsumers(12);//消费者个数
//        //设置确认模式为手工确认
//        container.setAcknowledgeMode(AcknowledgeMode.MANUAL);
//        container.setMessageListener(handlerService);
//        container.addQueues(queue);
        listenerContainerList.add(simpleMessageListenerContainer);
    }


    /**
     * 绑定一个Exchange到一个匹配型Exchange 使用一个routingKey
     *
     * @param exchange
     * @param topicExchange
     * @param routingKey
     */
    public void addBinding(Exchange exchange, TopicExchange topicExchange, String routingKey) {
        Binding binding = BindingBuilder.bind(exchange).to(topicExchange).with(routingKey);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 去掉一个binding
     *
     * @param binding
     */
    public void removeBinding(Binding binding) {
        rabbitAdmin.removeBinding(binding);
    }


    @Override
    public void confirm(CorrelationData correlationData, boolean ack, String failMsg) {
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
}
