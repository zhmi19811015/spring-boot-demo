package com.ming.rabbitmqproduction.topic;

import lombok.Data;

import java.io.Serializable;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/13 9:49 PM
 */
@Data
public class User implements Serializable {
    private String id;
    private String userName;
    private String birDate;
    private Integer sex;
}
