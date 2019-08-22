package com.ming.springbootconditional.config;

import com.ming.springbootconditional.service.CmdService;
import com.ming.springbootconditional.service.impl.LinuxCmdService;
import com.ming.springbootconditional.service.impl.MacCmdService;
import com.ming.springbootconditional.service.impl.WindowCmdService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

/**
 * 条件配置类.
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-06 20:04
 */
@Configuration
public class CmdServiceConditionConfig {
    /**
     * 当WindowCondition方法中的matches返回true的时候，
     * WindowCmdService会被注入，否则不注入。
     */
    @Bean("cmdService")
    @Conditional(WindowCondition.class)
    public CmdService windowCmdService(){
        return new WindowCmdService();
    }

    /**
     * 当LinuxCondition方法中的matches返回true的时候，
     * LinuxCmdService会被注入，否则不注入。
     */
    @Bean("cmdService")
    @Conditional(LinuxCondition.class)
    public CmdService linuxCmdService(){
        return new LinuxCmdService();
    }

    /**
     * 当LinuxCondition方法中的matches返回true的时候，
     * LinuxCmdService会被注入，否则不注入。
     */
    @Bean("cmdService")
    @Conditional(MacCondition.class)
    public CmdService macCmdService(){
        return new MacCmdService();
    }
}
