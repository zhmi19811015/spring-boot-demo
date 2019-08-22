package com.ming.nettydemo.netty;

import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/20 1:52 PM
 */
public class ChannelFutureListenerImpl implements ChannelFutureListener {
    @Override
    public void operationComplete(ChannelFuture channelFuture) throws Exception {
        System.out.println("=========isDone");
        if (channelFuture.isSuccess()){
            Thread.sleep(5000);
            System.out.println("===========isSuccess");
        }
    }
}
