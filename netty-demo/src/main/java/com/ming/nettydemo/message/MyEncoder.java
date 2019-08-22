package com.ming.nettydemo.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/21 9:09 AM
 */
public class MyEncoder extends MessageToByteEncoder<MessageProtocol>{
    @Override
    protected void encode(ChannelHandlerContext ctx, MessageProtocol msg, ByteBuf out) throws Exception {
        out.writeInt(msg.getContentLength());
        out.writeBytes(msg.getContent());
    }
}
