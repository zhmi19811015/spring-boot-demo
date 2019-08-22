package com.ming.nettydemo.client;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/6/17 2:23 PM
 */
public class ByteUtilTo {
    //将16进制字符串转换为byte[]
    public static byte[] toBytes(String str) {
        if(str == null || str.trim().equals("")) {
            return new byte[0];
        }

        byte[] bytes = new byte[str.length() / 2];
        for(int i = 0; i < str.length() / 2; i++) {
            String subStr = str.substring(i * 2, i * 2 + 2);
            bytes[i] = (byte) Integer.parseInt(subStr, 16);
        }

        return bytes;
    }

    /**
     * @see 将byte[]数组转换为String字符串
     * @author Herman.Xiong
     * @date 2014年5月5日 17:15:42
     * @param data byte数组
     * @return String 转换后的字符串
     */
    public static String byteToArray(byte[]data){
        String result="";
        for (int i = 0; i < data.length; i++) {
            result+=Integer.toHexString((data[i] & 0xFF) | 0x100).toUpperCase().substring(1, 3);
        }
        return result;
    }
}
