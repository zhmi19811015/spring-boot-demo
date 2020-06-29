package com.ming.springbootfastdfs;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.lang.Console;
import cn.hutool.http.HttpUtil;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-11-18 19:31
 */
public class Test {
    public static void main(String[] args) {
        Map<String,Object> param = new HashMap<>();
        String fielStr = "/Users/zhangiming/Desktop/123.png";
        Console.log(new File(fielStr).exists());
        param.put("file", FileUtil.file(fielStr));
        param.put("output","json");
        String reuslt = HttpUtil.post("http://134.175.89.119:8081/group1/upload",param);
        Console.log(reuslt);
        //http://134.175.89.119:8081/group1/default/20191118/19/43/1/123.png

        //http://134.175.89.119:8081/group1/default/20191118/19/47/1/123.png
    }
}
