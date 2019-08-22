package com.ming.springbootredis.job;

import cn.hutool.core.date.DateTime;
import com.ming.springbootredis.config.MyRedisTemplate;
import com.ming.springbootredis.service.RedisLock;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * 集群调度，重复执行问题，使用redis分布式锁控制
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/8 9:44 AM
 */
@Component
@Slf4j
public class SchedulerTest {
    @Autowired
    private MyRedisTemplate redisTemplate;
    //每天3：05执行
    @Scheduled(cron = "0 0/2 * * * ?")
    public void testTasks2() {
        RedisLock lock = new RedisLock(redisTemplate, "testJob_lock", 10000, 20000);
        boolean isLock = false;
        try {
            isLock = lock.lock();
            if(isLock) {
                //需要加锁的代码
                log.info("获取到锁，执行业务流程:"+Thread.currentThread().getId()+".time="+System.currentTimeMillis());
                log.info("定时任务执行时间：" + DateTime.now().toString());

                Thread.sleep(1000*30);
            }else{
                log.error("没有获取到锁："+Thread.currentThread().getId()+".time="+System.currentTimeMillis());
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }finally {
            //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
            //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
            if(isLock){
                lock.unlock();
            }

        }
    }
}
