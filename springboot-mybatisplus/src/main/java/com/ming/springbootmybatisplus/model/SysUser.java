package com.ming.springbootmybatisplus.model;

import lombok.Data;

import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-23 21:47
 */
@Data
public class SysUser {
    private String id;
    private String name;
    private Integer sex;
    private Date birthDate;
}
