package com.ming.springbootmybatis.vo;

import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/23 8:46 AM
 */
@Data
public class UserVo extends PageVo{
    private String name;
    private Integer sex;
}
