package com.ming.springioc.domain;

import com.ming.springioc.annotion.MyIoc;
import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-21 19:43
 */
@MyIoc
@Data
public class User {
    private Student student;
}
