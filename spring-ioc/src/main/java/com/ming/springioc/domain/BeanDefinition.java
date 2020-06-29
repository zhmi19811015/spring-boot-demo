package com.ming.springioc.domain;

import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-21 19:38
 */
@Data
public class BeanDefinition {
    private String className;
    private String alias;
    private String superNames;
}
