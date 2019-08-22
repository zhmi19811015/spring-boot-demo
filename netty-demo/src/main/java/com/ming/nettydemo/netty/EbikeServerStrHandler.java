package com.ming.nettydemo.netty;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/21 8:28 AM
 */
public class EbikeServerStrHandler extends SimpleChannelInboundHandler<String>{
    private int count = 0;
    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, String msg) throws Exception {
        System.out.println(msg);
//        byte[] buffer = new byte[msg.readableBytes()];
//        msg.readBytes(buffer);
//        String message = new String(buffer, Charset.forName("UTF-8"));
//        System.out.println("server recive:"+message);
//        System.out.println("server recive count:"+(++count));

    }


}
