package com.ming.springbootredis.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

/**
 * redis连接器
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/16 2:06 PM
 */
@Configurable
public class RedisConnConfig {

    /**
     * 配置Jedis连接器:
     *fefew
     * @return  org.springframework.data.redis.connection.jedis.JedisConnectionFactory
     * @author  zhangming
     * @date  2019/7/16 2:08 PM
     */
    @Bean
    public JedisConnectionFactory redisConnectionFactory(){
        RedisStandaloneConfiguration config = new RedisStandaloneConfiguration("server",6379);
        return new JedisConnectionFactory(config);
    }

    @Bean
    public LettuceConnectionFactory redisConnectionFactory1(){
       // return new LettuceConnectionFactory(new RedisSocketConfiguration("/var/run/redis.sock"));
        return new LettuceConnectionFactory(new RedisStandaloneConfiguration("server", 6379));
    }
    
    
}
