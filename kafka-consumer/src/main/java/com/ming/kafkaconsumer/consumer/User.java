package com.ming.kafkaconsumer.consumer;

import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/14 5:32 PM
 */
@Data
public class User {
    private String id;
    private String userName;
    private Integer sex;
}
