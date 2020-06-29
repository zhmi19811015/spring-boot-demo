package com.ming.springbootbase.config;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.jedis.JedisConnection;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 重写RedisTemplate,加入选库
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/7 5:24 PM
 */
public class MyRedisTemplate extends RedisTemplate {
    public static ThreadLocal<Integer> indexdb = new ThreadLocal<Integer>(){
        @Override protected Integer initialValue() { return 0; }
    };

    @Override
    protected RedisConnection preProcessConnection(RedisConnection connection, boolean existingConnection) {
        try {
            Integer dbIndex = indexdb.get();
           // Console.log("redis db index:"+dbIndex);
            //如果设置了dbIndex
            if (dbIndex != null) {
                if (connection instanceof JedisConnection) {
                    if (((JedisConnection) connection).getNativeConnection().getDB().intValue() != dbIndex) {
                        connection.select(dbIndex);
                    }
                } else {
                    connection.select(dbIndex);
                }
            } else {
                connection.select(0);
            }
        } finally {
            indexdb.remove();
        }
        return super.preProcessConnection(connection, existingConnection);
    }
}
