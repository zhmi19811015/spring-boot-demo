package com.ming.job;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/7 10:37 PM
 */
@Component
@Slf4j
public class SchedulerTest {
    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    //每隔2秒执行一次
    @Scheduled(fixedRate = 2000)
    public void testTasks1() {
        log.info("定时任务执行时间11："+Thread.currentThread().getName()+"==" + dateFormat.format(new Date()));
//        try{
//            int a = 6/0;
//        }catch (Exception e){
//            log.error("错误",e);
//        }
    }

    //每天3：05执行
    @Scheduled(cron = "0 0/2 * * * ?")
    public void testTasks2() {
        log.info("定时任务执行时间：" +Thread.currentThread().getName()+"==" + dateFormat.format(new Date()));
    }

}
