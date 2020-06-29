package com.ming.ftp;

import cn.hutool.core.io.FileUtil;
import cn.hutool.extra.ftp.Ftp;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/5/9 9:23 下午
 */
public class MyFtpUtil {
    public static void upload() throws Exception{
        Ftp ftp = new Ftp("192.168.2.213",21,"ftp","ftp");
       // ftp.cd("remote_export");
        ftp.upload("/remote_export", FileUtil.file("f:/zhangming/123.txt"));
        ftp.close();
    }
}
