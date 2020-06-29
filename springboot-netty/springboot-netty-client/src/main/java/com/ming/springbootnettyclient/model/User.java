package com.ming.springbootnettyclient.model;

import lombok.Data;

import java.io.Serializable;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/4/9 5:23 下午
 */
@Data
public class User implements Serializable {
    private String name;
    private int id;
    private int sex;
    private int age;
}
