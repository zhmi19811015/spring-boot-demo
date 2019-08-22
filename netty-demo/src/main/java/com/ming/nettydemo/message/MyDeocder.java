package com.ming.nettydemo.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ReplayingDecoder;

import java.util.List;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/21 9:04 AM
 */
public class MyDeocder extends ReplayingDecoder<Void>{
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf in, List<Object> out) throws Exception {
        int length = in.readInt();
        byte[] content = new byte[length];
        in.readBytes(content);

        MessageProtocol messageProtocol = new MessageProtocol(length,content);

        out.add(messageProtocol);
    }
}
