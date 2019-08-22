package com.ming.springbootredis;

import lombok.Data;

import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/20 4:39 PM
 */
@Data
public class RedisMapPojo {
    private String userId;
    private String userName;
    private Date birthDate;
}
