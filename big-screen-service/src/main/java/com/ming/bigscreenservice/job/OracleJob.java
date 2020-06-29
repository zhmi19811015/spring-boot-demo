package com.ming.bigscreenservice.job;

import cn.hutool.core.date.DateUtil;
import cn.hutool.http.HttpUtil;
import com.alibaba.fastjson.JSON;
import com.ming.bigscreenservice.bo.DataBaseBO;
import com.ming.bigscreenservice.util.RedisUtil;
import com.ming.bigscreenservice.mapper.OracleSystemMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.HashMap;

/**
 * 数据库统计JOB
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/18 8:56 上午
 */
@Component
@Slf4j
public class OracleJob {
    @Value("${database_owner}")
    private String dataBaseOwner;
    @Value("${big_data_api_url}")
    private String bigDataApiUrl;
    //    @Value("${database_tablespace_name}")
//    private String databaseTablespaceName;
    @Autowired
    private OracleSystemMapper oracleSystemMapper;
    @Autowired
    private RedisUtil redisUtil;
    private final static String REDIS_KEY = "data_base_info";

    //每天凌晨12：30点执行0 0 1 * * ?   0 30 0 * * ?
    //@Scheduled(fixedRate = 11 * 1000)
    @Scheduled(cron = "0 30 0 * * ?")
    public void task() {
        log.info("执行数据库统计");
        long benginTime = System.currentTimeMillis();
        //long dataBasesize = oracleSystemMapper.getTableSpaceSize(dataBaseOwner);

        long dataBaseCount = oracleSystemMapper.getTotalCount(dataBaseOwner);
        long esCount = getESCount();

        long count = dataBaseCount + esCount;

        Object objectJson = redisUtil.get(REDIS_KEY);
        DataBaseBO dataBaseBO = new DataBaseBO();
        if (objectJson == null) {
            dataBaseBO.setCount(count);
            dataBaseBO.setDayAvgCount(0L);
            dataBaseBO.setDayPeakCount(0L);
        } else {
            dataBaseBO = JSON.parseObject(objectJson.toString(), DataBaseBO.class);
            long upCount = dataBaseBO.getCount();
            long upDayAvgCount = dataBaseBO.getDayAvgCount();
            long upDayPeakCount = dataBaseBO.getDayPeakCount();
            //今天入库的
            long dayOnCount = count - upCount;
            dataBaseBO.setCount(count);
            if (dayOnCount > 0) {
                long avg = (dayOnCount + upDayAvgCount) / 2;
                dataBaseBO.setDayAvgCount(avg);
                //平均值大于上次高峰值
                if (avg > upDayPeakCount) {
                    dataBaseBO.setDayPeakCount(avg);
                }
            }
        }
        dataBaseBO.setDateStr(DateUtil.now());
        redisUtil.set(REDIS_KEY, JSON.toJSONString(dataBaseBO));


        long endTime = System.currentTimeMillis();
        log.info("计算数据数量。花费时间(ms):" + (endTime - benginTime));
    }


    private long getESCount() {
        String str = HttpUtil.get(bigDataApiUrl + "getEsState.rest");
        HashMap hashMap = JSON.parseObject(str, HashMap.class);
        return Long.parseLong(hashMap.get("totalCount").toString());
        //return 56455645L;
    }
}
