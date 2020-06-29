package com.ming.bigscreenservice.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/18 8:24 上午
 */
@Data
public class MonitorBO {
    private int cpuHeNum;
    private BigDecimal cpu;
    //磁盘IO 字节
    private long diskIO;
    //网络IO 字节
    private long netIO;
    //磁盘总容量 字节
    private long diskTotal;
    //磁盘使用容量 字节
    private long diskUse;
    //内存 字节
    private long memory;
    //内存使用容量 字节
    private long memoryUse;
    private String ip;
    private String dataStr;
    //磁盘利用率  百分比
    private BigDecimal diskRatio;
}
