package com.ming.bigscreenservice.bo;

import lombok.Data;

import java.math.BigDecimal;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/3/18 8:25 上午
 */
@Data
public class DataBO {
    private int cpuHeNum;
    //总内存 B
    private long memory;
    //已使用内存 B
    private long useMemory;
    //上次网络读写之和 B
    private long upNetWriteAndRead;
    //本次网络读写之和 B
    private long nowNetWriteAndRead;
    private BigDecimal cpu;
    //磁盘 B
    private long disk;
    //已使用磁盘  b
    private long useDisk;
    //上次磁盘读写之和 B
    private long upDiskWriteAndRead;

    //本次磁盘读写之和 B
    private long nowDiskWriteAndRead;
    private String dateNow ;

    private long upDiskTime;
    private long nowDiskTime;
}
