package com.ming.springioc.domain;

import com.ming.springioc.annotion.MyIoc;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-21 19:44
 */
@MyIoc
public class Student {
    public String play(){
        return "student"+ this.toString();
    }
}
