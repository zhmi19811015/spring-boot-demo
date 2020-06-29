package com.ming.bigscreenservice.bo;

import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/18 9:27 上午
 */
@Data
public class DataBaseBO {
    //数据库与ES总记录数
    private long count;
    //日高峰
    private long dayPeakCount;
    //日平均数
    private long dayAvgCount;
    private String dateStr;
}
