//package com.ming.springbootmybatis.config;
//
//import com.github.xiaolyuh.manager.CacheManager;
//import com.github.xiaolyuh.manager.LayeringCacheManager;
//import org.springframework.beans.factory.annotation.Configurable;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.EnableAspectJAutoProxy;
//import org.springframework.data.redis.core.RedisTemplate;
//
///**
// * 缓存管理--多级缓存配置
// *
// * @author zhangming
// * @version 1.0
// * @date 2019/7/15 11:27 AM
// */
//@Configurable
//@EnableAspectJAutoProxy
//public class CacheManagerConfig {
//
//    @Bean
//    public CacheManager cacheManager(RedisTemplate<String, Object> redisTemplate) {
//        LayeringCacheManager layeringCacheManager = new LayeringCacheManager(redisTemplate);
//        // 默认开启统计功能
//        layeringCacheManager.setStats(true);
//        return layeringCacheManager;
//    }
//}
