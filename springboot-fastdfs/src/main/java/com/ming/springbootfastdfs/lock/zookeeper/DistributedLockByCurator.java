/**
 * @Project spring-boot-demo
 * @Package com.ming.lock.zookeeper
 * @Description: TODO
 * @author zhangming
 * @date 2019/3/1 14:45
 * @version V1.0
 */
package com.ming.springbootfastdfs.lock.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.cache.PathChildrenCache;
import org.apache.curator.framework.recipes.cache.PathChildrenCacheEvent;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.ZooDefs;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CountDownLatch;

@Service
public class DistributedLockByCurator implements InitializingBean {

    private static final Logger logger = LoggerFactory.getLogger(DistributedLockByCurator.class);

    private final static String ROOT_PATH_LOCK = "rootlock";
    private CountDownLatch countDownLatch = new CountDownLatch(1);

    @Autowired
    private CuratorFramework curatorFramework;

    /**
     * 获取分布式锁
     */
    public boolean acquireDistributedLock(String path) {
        String keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
        boolean res = false;
//        while (true) {
//            try {
//                curatorFramework
//                        .create()
//                        .creatingParentsIfNeeded()
//                        .withMode(CreateMode.EPHEMERAL)
//                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
//                        .forPath(keyPath);
//                System.out.println(Thread.currentThread().getName()+";success to acquire lock for path:"+keyPath);
//                res = true;
//                break;
//            } catch (Exception e) {
//                //没有获取到锁，继续等待
//                System.out.println(Thread.currentThread().getName()+";failed to acquire lock for path:{}"+keyPath);
//                System.out.println(Thread.currentThread().getName()+";while try again .......");
//                try {
//                    if (countDownLatch.getCount() <= 0) {
//                        countDownLatch = new CountDownLatch(1);
//                    }
//                    countDownLatch.await();
//                } catch (InterruptedException e1) {
//                    e1.printStackTrace();
//                }
//            }
//        }


        try {
            curatorFramework
                    .create()
                    .creatingParentsIfNeeded()
                    .withMode(CreateMode.EPHEMERAL)
                    .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                    .forPath(keyPath);
            System.out.println(Thread.currentThread().getName()+";success to acquire lock for path:"+keyPath);
            res = true;
        } catch (Exception e) {
            //没有获取到锁，继续等待
            System.out.println(Thread.currentThread().getName()+";failed to acquire lock for path:{}"+keyPath);
            System.out.println(Thread.currentThread().getName()+";while try again .......");
//            try {
//                if (countDownLatch.getCount() <= 0) {
//                    countDownLatch = new CountDownLatch(1);
//                }
//                countDownLatch.await();
//            } catch (InterruptedException e1) {
//                e1.printStackTrace();
//            }
        }

        return res;
    }

    /**
     * 释放分布式锁
     */
    public boolean releaseDistributedLock(String path) {
        try {
            String keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
            if (curatorFramework.checkExists().forPath(keyPath) != null) {
                curatorFramework.delete().forPath(keyPath);
            }
        } catch (Exception e) {
            System.out.println("failed to release lock");
            return false;
        }
        return true;
    }

    /**
     * 创建 watcher 事件
     */
    private void addWatcher(String path) throws Exception {
        String keyPath;
        if (path.equals(ROOT_PATH_LOCK)) {
            keyPath = "/" + path;
        } else {
            keyPath = "/" + ROOT_PATH_LOCK + "/" + path;
        }
        final PathChildrenCache cache = new PathChildrenCache(curatorFramework, keyPath, false);
        cache.start(PathChildrenCache.StartMode.POST_INITIALIZED_EVENT);
        cache.getListenable().addListener((client, event) -> {
            if (event.getType().equals(PathChildrenCacheEvent.Type.CHILD_REMOVED)) {
                String oldPath = event.getData().getPath();
                System.out.println(Thread.currentThread().getName()+";success to release lock for path:"+oldPath);
                if (oldPath.contains(path)) {
                    //释放计数器，让当前的请求获取锁
                    countDownLatch.countDown();
                }
            }
        });
    }


    /**
     * 创建父节点，并创建永久节点
     * @throws Exception
     */
    @Override
    public void afterPropertiesSet() throws Exception {
        curatorFramework = curatorFramework.usingNamespace("lock-namespace");
        String path = "/" + ROOT_PATH_LOCK;
        try {
            if (curatorFramework.checkExists().forPath(path) == null) {
                curatorFramework.create()
                        .creatingParentsIfNeeded()
                        .withMode(CreateMode.PERSISTENT)
                        .withACL(ZooDefs.Ids.OPEN_ACL_UNSAFE)
                        .forPath(path);
            }
            addWatcher(ROOT_PATH_LOCK);
            logger.info("root path 的 watcher 事件创建成功");
        } catch (Exception e) {
            logger.error("connect zookeeper fail，please check the log >> {}", e.getMessage(), e);
        }
    }
}
