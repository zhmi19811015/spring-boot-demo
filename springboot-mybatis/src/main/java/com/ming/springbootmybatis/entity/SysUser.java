package com.ming.springbootmybatis.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/13 10:58 PM
 */
@Table(name = "sys_user")
@Data
public class SysUser{
    //private static final long serialVersionUID = 8655851615465363473L;

    @Id
    private String id;
    private String name;
    private Integer sex;
    @Column(name = "birth_date")
    private Date birthDate;
}
