package com.ming.springbootresourcesfile;

import org.springframework.core.io.*;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/4/16 5:10 下午
 */
public class TestResourceLoader {
    public static void main(String[] args) {
        ResourceLoader resourceLoader = new DefaultResourceLoader();

        Resource fileResource1 = resourceLoader.getResource("D:/Users/chenming673/Documents/spark.txt");
        System.out.println("fileResource1 is FileSystemResource:" + (fileResource1 instanceof FileSystemResource));

        Resource fileResource2 = resourceLoader.getResource("/Users/chenming673/Documents/spark.txt");
        System.out.println("fileResource2 is ClassPathResource:" + (fileResource2 instanceof ClassPathResource));

        Resource urlResource1 = resourceLoader.getResource("file:/Users/chenming673/Documents/spark.txt");
        System.out.println("urlResource1 is UrlResource:" + (urlResource1 instanceof UrlResource));

        Resource urlResource2 = resourceLoader.getResource("http://www.baidu.com");
        System.out.println("urlResource1 is urlResource:" + (urlResource2 instanceof  UrlResource));
    }

}

