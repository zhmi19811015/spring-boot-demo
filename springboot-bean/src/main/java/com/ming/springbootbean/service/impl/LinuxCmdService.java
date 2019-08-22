package com.ming.springbootbean.service.impl;

import com.ming.springbootbean.service.CmdService;
import org.springframework.stereotype.Service;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-06 20:50
 */
@Service("linuxCmdService")
public class LinuxCmdService implements CmdService {
    @Override
    public String print() {
        return "linux";
    }
}
