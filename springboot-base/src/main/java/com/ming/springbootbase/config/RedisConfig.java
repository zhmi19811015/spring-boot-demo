package com.ming.springbootbase.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.RedisPassword;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import redis.clients.jedis.JedisPoolConfig;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/7 5:19 PM
 */
@Configuration
@Slf4j
public class RedisConfig {
    @Value("${redis.hostName}")
    private String hostName;

    @Value("${redis.password}")
    private String password;

    @Value("${redis.port}")
    private Integer port;

    @Value("${redis.maxIdle}")
    private Integer maxIdle;

    @Value("${redis.timeout}")
    private Integer timeout;

    @Value("${redis.maxTotal}")
    private Integer maxTotal;

    @Value("${redis.maxWaitMillis}")
    private Integer maxWaitMillis;

    @Value("${redis.minEvictableIdleTimeMillis}")
    private Integer minEvictableIdleTimeMillis;

    @Value("${redis.numTestsPerEvictionRun}")
    private Integer numTestsPerEvictionRun;

    @Value("${redis.timeBetweenEvictionRunsMillis}")
    private long timeBetweenEvictionRunsMillis;

    @Value("${redis.testOnBorrow}")
    private boolean testOnBorrow;

    @Value("${redis.testWhileIdle}")
    private boolean testWhileIdle;


    /**
     * 功能描述: redis自动
     *
     * @param  1
     * @return  org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author  zhangming
     * @date  2019/7/13 9:33 PM
     */
    @Bean
    public JedisConnectionFactory JedisConnectionFactory(){
        RedisStandaloneConfiguration redisStandaloneConfiguration = new RedisStandaloneConfiguration ();
        redisStandaloneConfiguration.setHostName(hostName);
        redisStandaloneConfiguration.setPort(port);
        //由于我们使用了动态配置库,所以此处省略
        //redisStandaloneConfiguration.setDatabase(database);
        redisStandaloneConfiguration.setPassword(RedisPassword.of(password));
//        JedisClientConfiguration.JedisClientConfigurationBuilder jedisClientConfiguration = JedisClientConfiguration.builder();
//        jedisClientConfiguration.connectTimeout(Duration.ofMillis(timeout));
//
//
//        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
//                jedisClientConfiguration.build());

        JedisClientConfiguration.JedisPoolingClientConfigurationBuilder jpb =
                (JedisClientConfiguration.JedisPoolingClientConfigurationBuilder) JedisClientConfiguration.builder();
        JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
        jedisPoolConfig.setMaxIdle(maxIdle);
        jedisPoolConfig.setTestOnBorrow(testOnBorrow);
        jedisPoolConfig.setTestWhileIdle(testWhileIdle);
        jedisPoolConfig.setMinEvictableIdleTimeMillis(minEvictableIdleTimeMillis);
        jedisPoolConfig.setMaxTotal(maxTotal);
        jedisPoolConfig.setMaxWaitMillis(maxWaitMillis);

        jpb.poolConfig(jedisPoolConfig);

        JedisConnectionFactory factory = new JedisConnectionFactory(redisStandaloneConfiguration,
                jpb.build());
        return factory;

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
