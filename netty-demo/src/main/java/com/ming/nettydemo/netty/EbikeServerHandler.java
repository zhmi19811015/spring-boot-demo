package com.ming.nettydemo.netty;

import com.ming.nettydemo.common.constant.NettyConstant;
import com.ming.nettydemo.dto.Location;
import com.ming.nettydemo.util.ByteUtil;
import com.ming.nettydemo.util.ClientAnalysis;
import com.ming.nettydemo.util.ConvertCode;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.util.Attribute;
import io.netty.util.AttributeKey;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

import java.util.Date;

@Slf4j
public class EbikeServerHandler extends ChannelDuplexHandler {
	// private static Map<String, Channel> mapChannel = new
	// ConcurrentHashMap<String, Channel>();
	public static final AttributeKey<NettyChannel> NETTY_CHANNEL_KEY = AttributeKey.valueOf("netty.channel");

//	@Override
//	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
//		//log.info("客户端数据：" + msg);
//		ByteBuf buf = (ByteBuf)msg;
//		byte[] bytes = new byte[buf.readableBytes()];
//		//复制内容到字节数组bytes
//		buf.readBytes(bytes);
//		String data = ConvertCode.receiveHexToString(bytes);
//		log.info("客户端数据：" + data);
//
//		log.debug("数据对象长度：" + ((byte[]) msg).length);
//		//System.out.println(data.substring(0, 4));
//		String msgType = data.substring(0, 4).toLowerCase();//
//		Channel channel = ctx.channel();
//		String devId = null;
//		switch (msgType) {
//			case NettyConstant.MsgType.REGISTER://ec011234
//				devId = ClientAnalysis.getDeviceId(data);
//				setNettyChannel(ctx, devId);
//				channel.write(ByteUtil.toBytes("ec020001000368"));
//				channel.flush();
//				break;
//			case NettyConstant.MsgType.LOCATION://ec01
//				devId = getNettyChannel(ctx);
//				Location location = ClientAnalysis.setLocation(data, devId);
//				//new Db().insert(location);
//				log.info("位置信息:" + location);
//				break;
//			default:
//				break;
//		}
//
//		// if (data.startsWith("ec01")) {
//		// // 获取输出流，响应客户端的请求
//		// Channel channel = ctx.channel();
//		// // ctx.channel().writeAndFlush("ec020001000368");
//		// channel.write(ByteUtil.toBytes("ec020001000368"));
//		// // channel.write("ec020001000368");
//		// channel.flush();
//		// // 执行600S上传定位信息
//		// // os.write(ByteUtil.toBytes("ec030002000a0f68"));
//		// // os.flush();
//		// }
//	}

	/**
	 * 公用回写数据到客户端的方法
	 * @param 需要回写的字符串
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

	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
		//log.info("客户端数据：" + msg);
		byte[] b = (byte[]) msg;
		String data = ByteUtil.bytesToHex(b);
		log.info("client data:" + data);

		log.debug("client data length:" + ((byte[]) msg).length);
		//System.out.println(data.substring(0, 4));
		String msgType = data.substring(0, 4).toLowerCase();//
		Channel channel = ctx.channel();
		String devId = null;
		switch (msgType) {
		case NettyConstant.MsgType.REGISTER://ec011234
			devId = ClientAnalysis.getDeviceId(data);
			setNettyChannel(ctx, devId);
			channel.write(ByteUtil.toBytes("ec020001000368"));
			channel.flush();
			break;
		case NettyConstant.MsgType.LOCATION://ec01
			//devId = getNettyChannel(ctx);

			Location location = ClientAnalysis.setLocation(data, "1111111");
			//new Db().insert(location);
			log.info("location msg:" + location);
			break;
		default:
			break;
		}

		// if (data.startsWith("ec01")) {
		// // 获取输出流，响应客户端的请求
		// Channel channel = ctx.channel();
		// // ctx.channel().writeAndFlush("ec020001000368");
		// channel.write(ByteUtil.toBytes("ec020001000368"));
		// // channel.write("ec020001000368");
		// channel.flush();
		// // 执行600S上传定位信息
		// // os.write(ByteUtil.toBytes("ec030002000a0f68"));
		// // os.flush();
		// }
	}

	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		log.info("channelReadComplete");
	}

	@Override
	public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
		String remoteAddress = ctx.channel().remoteAddress().toString();
		// ctx.channel().id
		log.info("客户端IP：" + remoteAddress);
		log.info("channelRegistered");

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

	@Override
	public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
		log.info("channelUnregistered");
	}

	@Override
	public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
		log.info("userEventTriggered");
	}

	@Override
	public void channelActive(ChannelHandlerContext ctx) throws Exception {
		log.info("channelActive");
	}

}
