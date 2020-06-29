package com.ming.rabbitmqpdemo.receive;

import com.ming.rabbitmqpdemo.core.QueueHandler;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-12-06 23:07
 */
public interface IMark  {
    QueueHandler getQueueHandler();
    void  close();
}
