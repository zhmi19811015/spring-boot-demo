package com.ming.springbootredis.util;

import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/20 4:40 PM
 */
public class DateUtil {
    /**
     * 获取随机日期
     * @param beginDate 起始日期
     * @param endDate 结束日期
     * @return
     */
    public static Date randomDate(Date beginDate, Date endDate){
        try {
            if(beginDate.getTime() >= endDate.getTime()){
                return null;
            }

            long date = random(beginDate.getTime(),endDate.getTime());

            return new Date(date);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private static long random(long begin,long end){
        long rtn = begin + (long)(Math.random() * (end - begin));
        if(rtn == begin || rtn == end){
            return random(begin,end);
        }
        return rtn;
    }
}
