package com.ming.nettydemo.client;

import com.ming.nettydemo.util.ByteUtil;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/17 12:03 PM
 */
public class BikeClientHandler extends ChannelInboundHandlerAdapter {
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        byte[] b = (byte[])msg;
        String str = ByteUtil.bytesToHex(b);
        System.out.println("clent message:"+str+"--->"+Thread.currentThread().getName());
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        System.err.println("clent exception");
    }
}
