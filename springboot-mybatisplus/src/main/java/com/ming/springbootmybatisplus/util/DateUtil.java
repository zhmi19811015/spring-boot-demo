package com.ming.springbootmybatisplus.util;

import cn.hutool.core.date.DateField;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-25 11:15
 */
public final class DateUtil {

    public static void main(String[] args) {
        Date startDate = cn.hutool.core.date.DateUtil.offset(new Date(), DateField.DAY_OF_WEEK, -2);
        String startD = cn.hutool.core.date.DateUtil.formatDateTime(startDate);
        Date endDate = cn.hutool.core.date.DateUtil.offset(new Date(), DateField.MINUTE, 20);
        String endD = cn.hutool.core.date.DateUtil.formatDateTime(endDate);


        Date date = randomDate(startD,endD);
        System.out.println(cn.hutool.core.date.DateUtil.formatDateTime(date));
    }

    /**
     * 获取随机日期
     * @param beginDate 起始日期，格式为：yyyy-MM-dd
     * @param endDate 结束日期，格式为：yyyy-MM-dd
     * @return
     */
    private static Date randomDate(String beginDate, String endDate){
        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date start = format.parse(beginDate);
            Date end = format.parse(endDate);

            if(start.getTime() >= end.getTime()){
                return null;
            }

            long date = random(start.getTime(),end.getTime());

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
