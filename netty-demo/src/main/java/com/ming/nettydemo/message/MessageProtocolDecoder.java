package com.ming.nettydemo.message;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * 消息解码器
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/5/30 4:07 PM
 */
@Slf4j
public class MessageProtocolDecoder extends ByteToMessageDecoder{
    /** 消息的长度 字节 。**/
    public final int BASE_LENGTH = 25;
    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, ByteBuf byteBuf, List<Object> list) throws Exception {
        log.info("messsage length:"+byteBuf.readableBytes());
//        //可读长度必须大于等于基本长度
//        if (byteBuf.readableBytes() >= BASE_LENGTH){
//            // 防止socket字节流攻击
//            // 防止，客户端传来的数据过大
//            // 因为，太大的数据，是不合理的
//            if (byteBuf.readableBytes()>2048){
//                byteBuf.skipBytes(byteBuf.readableBytes());
//            }
//            //记录包头开始的index
//            int beginReader;
//            while (true){
//                // 获取包头开始的index
//                beginReader = byteBuf.readerIndex();
//                log.info("记录包头开始的index:"+beginReader);
//                //标记包头开始的index
//                byteBuf.markReaderIndex();
//                //读取了协议开始标识，结束循环
//                int head = byteBuf.readInt();
//                log.info("读取的int:"+head);
//                if (head == NettyConstant.MsgType.HEAD_DATA){
//                    break;
//                }
//
//                //未读到包头，省略一个字节
//                //每次省略一个字节，去读取包头信息的开始标记
//                byteBuf.resetReaderIndex();
//                byteBuf.readByte();
//
//                //当省略一个字节后
//                //数据包的长度，又变得不满足，此时应该结束，等待后面的数据到来
//                if (byteBuf.readableBytes() < BASE_LENGTH){
//                    return;
//                }
//            }
//
//            //消息长度
//            int length = byteBuf.readInt();
//            //判断请求数据包数据是否到齐
//            if (byteBuf.readableBytes() < length){
//                // 还原读指针
//                byteBuf.readerIndex(beginReader);
//                return;
//            }
//
//            // 读取data数据
//            byte[] data = new byte[length];
//            byteBuf.readBytes(data);
//            /*int head = buffer.readInt();
//            int length = buffer.readInt();
//            byte[] data = new byte[length];
//            buffer.readBytes(data);*/
//            MessageProtocol protocol = new MessageProtocol(data.length, data);
//            list.add(protocol);
//
//        }

        //消息长度
        int length = byteBuf.readInt();
//        //判断请求数据包数据是否到齐
//        if (byteBuf.readableBytes() < length){
//            // 还原读指针
//            byteBuf.readerIndex(beginReader);
//            return;
//        }

        // 读取data数据
        byte[] data = new byte[length];
        byteBuf.readBytes(data);
            /*int head = buffer.readInt();
            int length = buffer.readInt();
            byte[] data = new byte[length];
            buffer.readBytes(data);*/
        MessageProtocol protocol = new MessageProtocol(data.length, data);
        list.add(protocol);

    }
}
