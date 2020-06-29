package com.ming.zkregistercenter.core;

import lombok.extern.slf4j.Slf4j;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;
import org.apache.zookeeper.data.Stat;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-21 19:12
 */
@Slf4j
public class ZkUtil {
    private static final String ZOOKEEER_URL = "134.175.89.119:2181";
    //超时时间 毫秒
    private static final int TIME_OUT = 5000;
    private static CuratorFramework curatorFramework;

    /**
     * 连接
     *
     * @author  zhangming
     * @date  2019-08-21 19:19
     */
    public static void connect(){
        curatorFramework = CuratorFrameworkFactory.builder()
                .connectString(ZOOKEEER_URL)
                .sessionTimeoutMs(TIME_OUT)
                .namespace("springboot-ming")
                .retryPolicy(new ExponentialBackoffRetry(1000,3)).build();
        //启动
        curatorFramework.start();
        log.info("zookeeper已经成功连接上-"+ZOOKEEER_URL);
    }


    /**
     * 判断节点是否存在
     *
     * @param nodeName 节点名称
     * @throws Exception
     */
    public static boolean checkExist(String nodeName) throws Exception {
        Stat stat = curatorFramework.checkExists().forPath("/"+nodeName);
        if (stat == null){
           return false;
        }
        return true;
    }

    /**
     * 创建永久节点
     *
     * @param nodeName 节点名称
     * @param nodeValue 节点值
     * @author  zhangming
     * @date  2019-08-21 19:24
     */
    public static void createNodePersistent(String nodeName,String nodeValue) throws Exception{
        createNode(nodeName,nodeValue,CreateMode.PERSISTENT);
        log.info("成功创建永久节点:"+nodeName);
    }

    /**
     * 创建临时节点
     *
     * @param nodeName 节点名称
     * @param nodeValue 节点值
     * @author  zhangming
     * @date  2019-08-21 19:24
     */
    public static void createNodeTemp(String nodeName,String nodeValue) throws Exception{
        createNode(nodeName,nodeValue,CreateMode.EPHEMERAL);
        log.info("成功创建临时节点:"+nodeName);
    }

    private static void createNode(String nodeName,String nodeValue,CreateMode createMode) throws Exception{
        curatorFramework.create()
                .creatingParentsIfNeeded()// 如果父节点不存在，则自动创建
                //节点模式，持久
                .withMode(createMode)
                .forPath("/"+nodeName,nodeValue.getBytes());
    }
}
