package com.ming.demo.hutool;

import cn.hutool.core.lang.Console;
import cn.hutool.core.util.XmlUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import java.io.File;
import java.util.Map;

/**
 * @Description: 类作用描述
 * @Author: zhangming
 * @CreateDate: 2019/4/24 11:21 AM
 * @Version: 1.0
 */

public class XmlTest {
//    @Value("classpath:xml/test.xml")
//    private Resource testXml;

    public  void test(){
        String path = this.getClass().getClassLoader().getResource("xml/test1.xml").getPath();

       // path += "xml/test1.xml";
        File file = new File(path);
        Document document = XmlUtil.readXML(file);
        String str = XmlUtil.toStr(document);
        Console.log(str);
        Map<String, Object> map =  XmlUtil.xmlToMap(str);
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            System.out.println("Key = " + entry.getKey() + ", Value = " + entry.getValue().toString());
        }
       // Console.log(str);
    }

    public static void main(String[] args) {
        new XmlTest().test();
    }


}
