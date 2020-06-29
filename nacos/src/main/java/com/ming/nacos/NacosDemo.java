package com.ming.nacos;

import com.alibaba.nacos.api.NacosFactory;
import com.alibaba.nacos.api.config.ConfigService;
import com.alibaba.nacos.api.config.listener.Listener;
import com.alibaba.nacos.api.exception.NacosException;

import java.util.Properties;
import java.util.concurrent.Executor;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-09-11 20:25
 */
public class NacosDemo {
    public static void main(String[] args) {
        //连接服务器
        //指定dataId groupId
        String serverAdd = "";
        String dataId = "";
        String groupId = "";
        Properties properties = new Properties();
        properties.put("serverAddr",serverAdd);

        try {
            ConfigService configService = NacosFactory.createConfigService(properties);
            String str = configService.getConfig(dataId,groupId,3000);
            System.out.println(str);

            //节点数据发送变化，回调
            configService.addListener(dataId, groupId, new Listener() {
                @Override
                public Executor getExecutor() {
                    return null;
                }

                @Override
                public void receiveConfigInfo(String s) {
                    System.out.println("新的数据:"+s);
                }
            });
        } catch (NacosException e) {
            e.printStackTrace();
        }
    }
}
