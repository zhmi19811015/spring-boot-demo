package com.ming.springbootftp;

import com.ming.springbootftp.core.FtpClientTemplate;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = SpringbootFtpApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SpringbootFtpApplicationTests {

    @Autowired
    private FtpClientTemplate ftpTemplate;

    @Test
    public void uploadFileTest() {
        File file = new File("/Users/zhangiming/temp/test.html");
        boolean uploadResult = ftpTemplate.uploadFile(file, "/");
        Assert.assertTrue(uploadResult);
    }


    @Test
    public void uploadFileThreadTest() {
        for (int i = 0; i < 100; i++) {
            Runnable runnable = () -> {
                File file = new File("/Users/zhangiming/temp/test.html");
                boolean uploadResult = ftpTemplate.uploadFile(file, "/");
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread 1-" + threadName + ":" + uploadResult);
            };
            runnable.run();
            new Thread(runnable).start();

            Runnable runnable1 = () -> {
                File file = new File("/Users/zhangiming/temp/test1.html");
                boolean uploadResult = ftpTemplate.uploadFile(file, "/");
                String threadName = Thread.currentThread().getName();
                System.out.println("Thread 2-" + threadName + ":" + uploadResult);
            };
            runnable1.run();
            new Thread(runnable1).start();
        }
    }


    @Test
    public void downloadFileTest() {
        boolean downloadResult = ftpTemplate.downloadFile("/", "2017061315035721.txt", "F:\\");
        Assert.assertTrue(downloadResult);
    }

    @Test
    public void deleteFileTest() {
        boolean deleteResult = ftpTemplate.deleteFile("/home/test", "2017061315035721.txt");
        Assert.assertTrue(deleteResult);
    }


}
