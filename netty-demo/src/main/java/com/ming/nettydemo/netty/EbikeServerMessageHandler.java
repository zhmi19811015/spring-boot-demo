package com.ming.nettydemo.netty;

import com.ming.nettydemo.message.MessageProtocol;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/21 9:12 AM
 */
public class EbikeServerMessageHandler extends SimpleChannelInboundHandler<MessageProtocol> {
    private int count = 0;

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, MessageProtocol msg) throws Exception {
        int length = msg.getContentLength();
        byte[] content = msg.getContent();


    }
}
