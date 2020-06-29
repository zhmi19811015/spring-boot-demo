package com.ming.rabbitmqpdemo.pojo;

import lombok.Data;

@Data
public class User{
    private String id;
    private String userName;
    private String birDate;
    private Integer sex;
}