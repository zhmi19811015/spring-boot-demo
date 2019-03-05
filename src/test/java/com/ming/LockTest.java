/**
 * @Project spring-boot-demo
 * @Package com.ming
 * @Description: TODO
 * @author zhangming
 * @date 2019/3/1 15:19
 * @version V1.0
 */
package com.ming;

import com.ming.lock.zookeeper.DistributedLockByCurator;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LockTest {
    @Autowired
    private DistributedLockByCurator distributedLockByCurator;

    private final static String PATH = "test";

    @Test
    public void test(){
        CountDownLatch countDownLatch = new CountDownLatch(10);
        for (int i = 0;i<=9;i++){
            new Thread(()->{
                boolean res = distributedLockByCurator.acquireDistributedLock(PATH);
                System.out.println("线程:"+Thread.currentThread().getName()+",是否获取到锁:"+res);
                if (res){
                    distributedLockByCurator.releaseDistributedLock(PATH);
                    System.out.println("线程:"+Thread.currentThread().getName()+",释放锁:"+res);
                }
               countDownLatch.countDown();
            }).start();
        }

        try{
            countDownLatch.await();
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Test
    public void test1(){
        for (int i = 0;i<1000;i++){
            System.out.println("cwefewfew-zhangming:"+i);
            try{
                Thread.sleep(2000);
            }catch (Exception e){

            }

        }
    }
}
