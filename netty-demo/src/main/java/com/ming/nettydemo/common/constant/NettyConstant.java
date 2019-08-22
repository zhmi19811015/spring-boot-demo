package com.ming.nettydemo.common.constant;

/**
 * netty 常量 
 * 
 * @author zhangming
 * @version 1.0
 * @date 2018年2月20日, 上午10:53:16
 */
public class NettyConstant {
	public static interface MsgType {
		/** 请求认证 . */
		final String REGISTER = "ec01";
		/** 位置 . */
		final String LOCATION = "ecbe";
		final int HEAD_DATA = 0XEC;

	}
}
