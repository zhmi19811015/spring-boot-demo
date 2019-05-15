package com.ming.nettydemo.dto;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 位置信息
 * 
 * @author zhangming
 * @version 1.0
 * @date 2018年2月14日, 上午12:19:48
 */
public class Location {
  /** 设备ID . */
  private String devId;
  /** 定位类型 1=GPS 0=LBS . */
  private Integer locationType;
  private float speed;
  private float longitude;
  private float latitude;
  private Date gdate;

  public String getDevId() {
    return devId;
  }

  public void setDevId(String devId) {
    this.devId = devId;
  }

  public Integer getLocationType() {
    return locationType;
  }

  public void setLocationType(Integer locationType) {
    this.locationType = locationType;
  }

  public float getSpeed() {
    return speed;
  }

  public void setSpeed(float speed) {
    this.speed = speed;
  }

  public float getLongitude() {
    return longitude;
  }

  public void setLongitude(float longitude) {
    this.longitude = longitude;
  }

  public float getLatitude() {
    return latitude;
  }

  public void setLatitude(float latitude) {
    this.latitude = latitude;
  }

  public Date getGdate() {
    return gdate;
  }

  public void setGdate(Date gdate) {
    this.gdate = gdate;
  }

  @Override
  public String toString() {
    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    StringBuilder sb = new StringBuilder();
    sb.append("设备ID：").append(devId).append("。定位方式:").append(locationType).append("。速度：").append(speed).append("。经度：")
        .append(longitude).append("。纬度：").append(latitude).append("。定位时间：").append(df.format(gdate));
    return sb.toString();
  }

}
