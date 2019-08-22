package com.ming.nettydemo.netty;

import com.ming.nettydemo.common.constant.NettyConstant;
import com.ming.nettydemo.dto.Location;
import com.ming.nettydemo.util.ByteUtil;
import com.ming.nettydemo.util.ClientAnalysis;
import com.ming.nettydemo.util.ConvertCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.handler.timeout.IdleState;
import io.netty.handler.timeout.IdleStateEvent;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import io.netty.util.ReferenceCountUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
//主要是为了多个handler 可以被多个channel安全地共享，也就是保证现场安全
@ChannelHandler.Sharable
public class EbikeServerHandler extends ChannelInboundHandlerAdapter {
	// private static Map<String, Channel> mapChannel = new
	// ConcurrentHashMap<String, Channel>();
	public static final AttributeKey<NettyChannel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");


	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		try{



			byte[] b = (byte[]) msg;
			String data = ByteUtil.bytesToHex(b);
			log.info("客户端数据：" + data);

			log.debug("数据对象长度：" + ((byte[]) msg).length);
			String msgType = data.substring(0, 4).toLowerCase();
			Channel channel = ctx.channel();
			String devId = null;
			switch (msgType) {
				case NettyConstant.MsgType.REGISTER:
					devId = ClientAnalysis.getDeviceId(data);
					setNettyChannel(ctx, devId);
					channel.writeAndFlush(ByteUtil.toBytes("ec020001000368"));//.addListener(new ChannelFutureListenerImpl());
					break;
				//ec01
				case NettyConstant.MsgType.LOCATION:
					devId = getNettyChannel(ctx);
					Location location = ClientAnalysis.setLocation(data, devId);
					//new Db().insert(location);
					log.info("位置信息:" + location);
					break;
				default:
					break;
			}
		}finally {
			ReferenceCountUtil.release(msg);
		}
	}


	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.debug("读完成：channelReadComplete");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		String remoteAddress = ctx.channel().remoteAddress().toString();
		// ctx.channel().id
		log.debug("channelRegistered client IP：" + remoteAddress);

	}


	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		log.debug("channelUnregistered");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		log.info("超时 userEventTriggered");
		if(evt instanceof IdleStateEvent){
            IdleState state = ((IdleStateEvent)evt).state();
            if (state == IdleState.READER_IDLE){
                // 心跳超时
				String devId = getNettyChannel(ctx);
                log.error("心跳超时,设备ID:"+devId);
                ctx.close();
                //这里也可以跑出异常，让exceptionCaught处理。
                //throw new Exception("idle exception");
            }
        }else{
		    super.userEventTriggered(ctx,evt);
        }
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.debug("连接激活 channelActive");
	}

	@Override
    public void exceptionCaught(ChannelHandlerContext ctx,Throwable cause) throws Exception{
	    cause.printStackTrace();
	    ctx.close();
    }


	private void setNettyChannel(ChannelHandlerContext ctx, String devId) {
		Attribute<NettyChannel> attr = ctx.attr(NETTY_CHANNEL_KEY);
		NettyChannel nChannel = attr.get();
		if (nChannel == null) {
			NettyChannel newNChannel = new NettyChannel(devId, new Date());
			nChannel = attr.setIfAbsent(newNChannel);

		}
		// else {
		// System.out.println("attributeMap 中是有值的");
		// System.out.println(nChannel.getName() + "=======" +
		// nChannel.getCreateDate());
		// }
		// System.out.println("HelloWorldC0ientHandler Active");
		// ctx.fireChannelActive();
		// return nChannel.getId();
	}

	private String getNettyChannel(ChannelHandlerContext ctx) {
		Attribute<NettyChannel> attr = ctx.attr(NETTY_CHANNEL_KEY);
		NettyChannel nChannel = attr.get();
		return nChannel.getId();
	}

	/**
	 * 公用回写数据到客户端的方法
	 * @param receiveStr 需要回写的字符串
	 * @param channel
	 * @param mark 用于打印/log的输出
	 * <br>//channel.writeAndFlush(msg);//不行
	 * <br>//channel.writeAndFlush(receiveStr.getBytes());//不行
	 * <br>在netty里，进出的都是ByteBuf，楼主应确定服务端是否有对应的编码器，将字符串转化为ByteBuf
	 */
	private void writeToClient(final String receiveStr, ChannelHandlerContext channel, final String mark) {
		try {
			ByteBuf bufff = Unpooled.buffer();//netty需要用ByteBuf传输
			bufff.writeBytes(ConvertCode.hexString2Bytes(receiveStr));//对接需要16进制
			channel.writeAndFlush(bufff).addListener(new ChannelFutureListener() {
				@Override
				public void operationComplete(ChannelFuture future) throws Exception {
					StringBuilder sb = new StringBuilder("");
					if(!StringUtils.isEmpty(mark)){
						sb.append("【").append(mark).append("】");
					}
					if (future.isSuccess()) {
						System.out.println(sb.toString()+"回写成功"+receiveStr);
						log.info(sb.toString()+"回写成功"+receiveStr);
					} else {
						System.out.println(sb.toString()+"回写失败"+receiveStr);
						log.error(sb.toString()+"回写失败"+receiveStr);
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("调用通用writeToClient()异常"+e.getMessage());
			log.error("调用通用writeToClient()异常：",e);
		}
	}

}
