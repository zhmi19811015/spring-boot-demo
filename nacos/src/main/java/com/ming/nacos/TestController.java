package com.ming.nacos;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import com.alibaba.nacos.spring.context.annotation.config.NacosPropertySource;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-11 20:12
 */
@NacosPropertySource(dataId = "ming",groupId = "dd",autoRefreshed = true)
public class TestController {

    //默认值hello.jiji 防止获取不到数据
    @NacosValue(value = "${info:hello.jiji}",autoRefreshed = true)
    private String info;
}
