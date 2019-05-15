package com.ming.nettydemo.util;

import com.ming.nettydemo.dto.Location;

import java.util.UUID;

/**
 * 客户端数据解析
 * 
 * @author zhangming
 * @version 1.0
 * @date 2018年2月13日, 下午11:31:39
 */
public class ClientAnalysis {

  public static String getUUID() {
    return UUID.randomUUID().toString().replace("-", "");
  }

  /**
   * 获取设备ID
   * 
   * @param data
   * @return
   * @author zhangming
   */
  public static String getDeviceId(String data) {
    return data.substring(8, 20);
  }

  /**
   * 位置信息
   * 
   * @param data
   * @param devId
   * @return
   * @author zhangming
   */
  public static Location setLocation(String data, String devId) {
    Location location = new Location();
    location.setDevId(devId);
    String str = data.substring(8, 10);
    if ("00".equals(str)) {
      location.setLocationType(0);
    } else {
      location.setLocationType(1);
    }
    // 速度
    str = data.substring(14, 16);

    int speed1 = ByteUtil.format16To10(str);
    str = data.substring(16, 18);
    int speed2 = ByteUtil.format16To10(str);
    StringBuilder sb = new StringBuilder();
    sb.append(speed1).append(".").append(speed2);
    str = sb.toString();
    location.setSpeed(Float.valueOf(str));
    // 经度
    str = data.substring(18, 20);
    int lng1 = ByteUtil.format16To10(str);
    str = data.substring(20, 26);
    int lng2 = ByteUtil.format16To10(str);
    sb = new StringBuilder();
    if (String.valueOf(lng2).length() == 4) {
      sb.append(lng1).append(".0").append(lng2);
      str = sb.toString();
    } else {
      sb.append(lng1).append(".").append(lng2);
      str = sb.toString();
    }
    location.setLongitude(Float.valueOf(str));
    // 纬度
    str = data.substring(26, 28);
    int lat1 = ByteUtil.format16To10(str);
    str = data.substring(28, 34);
    int lat2 = ByteUtil.format16To10(str);
    sb = new StringBuilder();
    if (String.valueOf(lat2).length() == 4) {
      sb.append(lat1).append(".0").append(lat2);
      str = sb.toString();
    } else {
      sb.append(lat1).append(".").append(lat2);
      str = sb.toString();
    }
    location.setLatitude(Float.valueOf(str));

    // 时间
    str = data.substring(34, 48);
    location.setGdate(ByteUtil.foramt16ToDate(str));
    // location.setDate(new Date());
    return location;
  }

  public static void main(String[] args) {
     Location location =
     setLocation("ecbe0014010101000075002a7e1f014ba81412020a0d10116568",
     "100000000002");
     System.out.println(location);

     String a = getDeviceId("ec01123410000000000268");
    System.out.println(a);
//    String s = getUUID();
//    System.out.println(s);
  }
}
