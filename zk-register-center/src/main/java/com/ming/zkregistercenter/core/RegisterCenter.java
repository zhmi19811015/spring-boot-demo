package com.ming.zkregistercenter.core;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-21 18:33
 */
@Component
@Order(1)
public class RegisterCenter implements CommandLineRunner {

    @Override
    public void run(String... args) throws Exception {
        //这里参数是要通过调用者的配置文件获取
        register("ceshi");
    }

    /**
     * 注册zoookeeper服务
     *
     * @param serverName 服务名称
     * @author  zhangming
     * @date  2019-08-21 19:33
     */
    private void register(String serverName) throws Exception{
        String address = getAddressHost();
        //这里端口是要通过调用者的配置文件中获取
        String port = "8100";
        String value = address+":"+port;
        ZkUtil.connect();
        //不存在该节点，创建永久节点
        if (!ZkUtil.checkExist(serverName)){
            //永久节点--服务名称。值为空，因为下面还存在子节点
            ZkUtil.createNodePersistent(serverName,"");
        }
        //创建临时节点

        ZkUtil.createNodeTemp(serverName+"/"+value,value);
    }



    /**
     * 获取本机IP
     *
     * @author  zhangming
     * @date  2019-08-21 19:10
     */
    private String getAddressHost() throws UnknownHostException {
        return InetAddress.getLocalHost().getHostAddress();
    }


}
