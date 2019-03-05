package com.ming.controller;

import com.ming.lock.zookeeper.DistributedLockByCurator;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/zookeerper")
public class ZookeerperController {

    @Autowired
    private DistributedLockByCurator distributedLockByCurator;

    private final static String PATH = "test";

    @GetMapping("/lock1")
    public Boolean getLock1() {
        Boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            //处理业务
            Thread.sleep(20000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLockByCurator.releaseDistributedLock(PATH);
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return flag;
    }

    @GetMapping("/lock2")
    public Boolean getLock2() {
        Boolean flag;
        distributedLockByCurator.acquireDistributedLock(PATH);
        try {
            Thread.sleep(15000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            flag = distributedLockByCurator.releaseDistributedLock(PATH);
        }
        flag = distributedLockByCurator.releaseDistributedLock(PATH);
        return flag;
    }
}
