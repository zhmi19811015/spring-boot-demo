package com.ming.nettydemo.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/30 4:05 PM
 */
public class MessageProtocolEncoder extends MessageToByteEncoder<MessageProtocol>{

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext, MessageProtocol smartEbikeProtocol, ByteBuf byteBuf) throws Exception {
        byteBuf.writeInt(smartEbikeProtocol.getHead_data());
        byteBuf.writeInt(smartEbikeProtocol.getContentLength());
        byteBuf.writeBytes(smartEbikeProtocol.getContent());
        byteBuf.writeInt(smartEbikeProtocol.getEnd_data());
    }
}
