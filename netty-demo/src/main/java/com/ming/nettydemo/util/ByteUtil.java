package com.ming.nettydemo.util;

import java.util.Calendar;
import java.util.Date;

public class ByteUtil {
	// private static Logger logger = Logger.getLogger(ByteUtil.class);

	/**
	 * 16进制字符串转10进制数值
	 * 
	 * @param str16
	 * @return
	 * @author zhangming
	 */
	public static int format16To10(String str16) {
		Integer x = Integer.parseInt(str16, 16);
		return x;
	}

	/**
	 * 将16进制字符串转换为byte[]
	 * 
	 * @param str
	 * @return
	 */
	public static byte[] toBytes(String str) {
		if (str == null || str.trim().equals("")) {
			return new byte[0];
		}

		byte[] bytes = new byte[str.length() / 2];
		for (int i = 0; i < str.length() / 2; i++) {
			String subStr = str.substring(i * 2, i * 2 + 2);
			bytes[i] = (byte) Integer.parseInt(subStr, 16);
		}

		return bytes;
	}

	/**
	 * 方法三： byte[] to hex string
	 * 
	 * @param bytes
	 * @return
	 */
	public static String bytesToHex(byte[] bytes) {
		StringBuilder buf = new StringBuilder(bytes.length * 2);
		for (byte b : bytes) { // 使用String的format方法进行转换
			buf.append(String.format("%02x", new Integer(b & 0xff)));
		}

		return buf.toString();
	}

	/**
	 * 16进制转换日期
	 * 
	 * @param str16
	 * @return
	 * @author zhangming
	 */
	public static Date foramt16ToDate(String str16) {
		String str1 = str16.substring(0, 2);
		int yearInt1 = format16To10(str1);
		String str2 = str16.substring(2, 4);
		int yearInt2 = format16To10(str2);
		StringBuilder sb = new StringBuilder();
		sb.append(yearInt1).append(yearInt2);
		int year = Integer.parseInt(sb.toString());
		// 月份
		str2 = str16.substring(4, 6);
		int month = format16To10(str2);
		// 天
		str2 = str16.substring(6, 8);
		int day = format16To10(str2);
		// 小时
		str2 = str16.substring(8, 10);
		int hour = format16To10(str2);
		// 小时
		str2 = str16.substring(10, 12);
		int mm = format16To10(str2);
		// 小时
		str2 = str16.substring(12, 14);
		int ss = format16To10(str2);

		Calendar cal = Calendar.getInstance();
		cal.set(year, month - 1, day, hour, mm, ss);

		return cal.getTime();
	}

	// public static void main(String[] args) {
	// // 记录debug级别的信息
	// logger.debug("This is debug message.");
	// // 记录info级别的信息
	// logger.info("This is info message.");
	// // // 记录error级别的信息
	// logger.error("This is error message.");
	// }
}
