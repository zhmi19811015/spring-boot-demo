package com.ming.springbootconditional.service.impl;

import com.ming.springbootconditional.service.CmdService;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-06 20:21
 */
public class MacCmdService implements CmdService {
    @Override
    public void print() {
        System.out.println("Mac cmd...");
    }
}
