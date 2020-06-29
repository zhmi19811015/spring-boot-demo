package com.ming.bigscreenservice.job;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSON;
import com.ming.bigscreenservice.bo.DataBO;
import com.ming.bigscreenservice.bo.MonitorBO;
import com.ming.bigscreenservice.config.MyRedisTemplate;
import com.ming.bigscreenservice.util.RedisLock;
import com.ming.bigscreenservice.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/18 8:21 上午
 */
@Component
@Slf4j
public class WinLiunxSystemJob {
//    @Value("${local_ip}")
//    private String localIp;

    @Autowired
    private RedisUtil redisUtil;
    @Autowired
    private MyRedisTemplate redisTemplate;

    private final static String REDIS_KEY = "service_data";
    private final static String REDIS_KEY_MONITOR = "service_monitor_list";
    private final static int TIME_S = 10;

    @Scheduled(fixedRate = 11 * 1000)
    public void task() {


        log.info("执行job:");
        RedisLock lock = new RedisLock(redisTemplate, "serverJob_lock", 3000, 8000);
        boolean isLock = false;
        try {
            isLock = lock.lock();
            if (isLock) {
               // log.info("获取锁任务,主机IP=：" + localIp);
                Map<Object, Object> map = redisUtil.hmget(REDIS_KEY);

                if (map != null) {
                    MonitorBO monitorBO = new MonitorBO();
                    List<MonitorBO> list = new ArrayList<>();
                    for (Map.Entry<Object, Object> entry : map.entrySet()) {
                        String mapKey = entry.getKey().toString();
                        String mapValue = entry.getValue().toString();
                        DataBO dataBO = JSON.parseObject(mapValue, DataBO.class);
                        monitorBO = setMonitorBO(dataBO, mapKey);
                        list.add(monitorBO);
                    }

                    long index = redisUtil.lGetListSize(REDIS_KEY_MONITOR);
                    if (index >= 7) {
                        Object obj = redisUtil.lRightPop(REDIS_KEY_MONITOR);
                        log.info("删除对象：" + obj);
                    }

                    redisUtil.lLeftSet(REDIS_KEY_MONITOR, JSON.toJSONString(list));
                }
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            //为了让分布式锁的算法更稳键些，持有锁的客户端在解锁之前应该再检查一次自己的锁是否已经超时，再去做DEL操作，因为可能客户端因为某个耗时的操作而挂起，
            //操作完的时候锁因为超时已经被别人获得，这时就不必解锁了。 ————这里没有做
            if (isLock) {
                lock.unlock();
            }

        }
    }

    private MonitorBO setMonitorBO(DataBO dataBO, String ip) {
        MonitorBO monitorBO = new MonitorBO();
        monitorBO.setIp(ip);
        monitorBO.setCpu(dataBO.getCpu());
        monitorBO.setDiskTotal(dataBO.getDisk());
        monitorBO.setDiskUse(dataBO.getUseDisk());
        monitorBO.setMemory(dataBO.getMemory());
        monitorBO.setMemoryUse(dataBO.getUseMemory());
        if (dataBO.getNowDiskWriteAndRead() > dataBO.getUpDiskWriteAndRead()) {
            long diskIO = (dataBO.getNowDiskWriteAndRead() - dataBO.getUpDiskWriteAndRead()) / TIME_S;
            monitorBO.setDiskIO(diskIO);
        } else {
            monitorBO.setDiskIO(0L);
        }
        if (dataBO.getNowNetWriteAndRead() > dataBO.getUpNetWriteAndRead()) {
            long netIO = (dataBO.getNowNetWriteAndRead() - dataBO.getUpNetWriteAndRead()) / TIME_S;
            monitorBO.setNetIO(netIO);
        } else {
            monitorBO.setNetIO(0L);
        }
        monitorBO.setDataStr(DateUtil.now());
        monitorBO.setCpuHeNum(dataBO.getCpuHeNum());
        if (dataBO.getNowDiskTime() > dataBO.getUpDiskTime()) {
            long time = dataBO.getNowDiskTime() - dataBO.getUpDiskTime();
            BigDecimal ab = BigDecimal.valueOf(time).divide(new BigDecimal(TIME_S * 1000), 2, BigDecimal.ROUND_UP).multiply(new BigDecimal(100));
            monitorBO.setDiskRatio(ab);
        } else {
            monitorBO.setDiskRatio(BigDecimal.ZERO);
        }


        return monitorBO;
    }
}
