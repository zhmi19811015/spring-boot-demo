package com.ming.springbootredis.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisNode;
import org.springframework.data.redis.connection.RedisSentinelConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/27 5:16 下午
 */
@Configuration
@PropertySource("classpath:redis.properties")
@Slf4j
public class RedisSentinelConfig {
    /**
     * 节点名称
     */
    @Value("${redis.nodes}")
    private String nodes;

    /**
     * Redis服务名称
     */
    @Value("${redis.masterName}")
    private String masterName;

    /**
     * 密码
     */
    @Value("${redis.password}")
    private String password;

    /**
     * 最大连接数
     */
    @Value("${redis.maxTotal}")
    private int maxTotal;

    /**
     * 最大空闲数
     */
    @Value("${redis.maxIdle}")
    private int maxIdle;

    /**
     * 最小空闲数
     */
    @Value("${redis.minIdle}")
    private int minIdle;

    /**
     * 连接超时时间
     */
    @Value("${redis.timeout}")
    private int timeout;




    public RedisSentinelConfiguration getRedisSentinelConfiguration(){
        RedisSentinelConfiguration configuration = new RedisSentinelConfiguration();
        String[] host = nodes.split(",");
        log.info("redis.nodes="+nodes);
        for(String redisHost : host){
            String[] item = redisHost.split(":");
            String ip = item[0];
            String port = item[1];
            configuration.addSentinel(new RedisNode(ip, Integer.parseInt(port)));
        }
        configuration.setMaster(masterName);
        configuration.setPassword(password);

        return configuration;
    }





    /**
     * 功能描述: redis自动
     *
     * @return  org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author  zhangming
     * @date  2019/7/13 9:33 PM
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(){
        RedisSentinelConfiguration redisSentinelConfiguration = getRedisSentinelConfiguration();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);

        jedisPoolConfig.setMaxTotal(maxTotal);

        JedisConnectionFactory jedisConnectionFactory =
                new JedisConnectionFactory(redisSentinelConfiguration, jedisPoolConfig);
        return jedisConnectionFactory;
    }


    /**
     * 功能描述: 实例化 RedisTemplate 对象
     *
     * @param redisConnectionFactory 1
     * @return  org.springframework.data.redis.core.RedisTemplate
     * @author  zhangming
     * @date  2019/5/7 5:22 PM
     */
    @Bean
    public MyRedisTemplate functionDomainRedisTemplate(RedisConnectionFactory redisConnectionFactory) {
        log.info("RedisTemplate实例化成功！");
        MyRedisTemplate redisTemplate = new MyRedisTemplate();
        initDomainRedisTemplate(redisTemplate, redisConnectionFactory);
        return redisTemplate;
    }

    /**
     * 功能描述: 引入自定义序列化
     *
     * @return  org.springframework.data.redis.serializer.RedisSerializer
     * @author  zhangming
     * @date  2019/5/7 5:24 PM
     */
    @Bean
    public RedisSerializer fastJson2JsonRedisSerializer() {
        return new FastJson2JsonRedisSerializer<Object>(Object.class);
    }

//    @Bean(name="redisUtil")
//    public RedisUtil redisUtil(MyRedisTemplate redisTemplate) {
//        log.info("RedisUtil注入成功！");
//        RedisUtil redisUtil = new RedisUtil();
//        redisUtil.setRedisTemplate(redisTemplate);
//        return redisUtil;
//    }


    /**
     * 功能描述: 设置数据存入 redis 的序列化方式,并开启事务
     *
     * @param redisTemplate 1
     * @param factory 2
     * @return  void
     * @author  zhangming
     * @date  2019/5/7 5:23 PM
     */
    private void initDomainRedisTemplate(MyRedisTemplate redisTemplate, RedisConnectionFactory factory) {
        //如果不配置Serializer，那么存储的时候缺省使用String，如果用User类型存储，那么会提示错误User can't cast to String！
        redisTemplate.setKeySerializer(new StringRedisSerializer());
        redisTemplate.setHashKeySerializer(new StringRedisSerializer());
//        redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
//        redisTemplate.setValueSerializer(fastJson2JsonRedisSerializer());
        redisTemplate.setHashValueSerializer(new StringRedisSerializer());
        redisTemplate.setValueSerializer(new StringRedisSerializer());
        // 开启事务
        redisTemplate.setEnableTransactionSupport(true);
        redisTemplate.setConnectionFactory(factory);



        redisTemplate.afterPropertiesSet();

    }


}
