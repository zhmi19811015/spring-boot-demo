package com.ming.nettydemo.dto;

import lombok.Data;

import java.util.Date;

/**
 * 位置信息
 * 
 * @author zhangming
 * @version 1.0
 * @date 2018年2月14日, 上午12:19:48
 */
@Data
public class Location {
  /** 设备ID . */
  private String devId;
  /** 定位类型 1=GPS 0=LBS . */
  private Integer locationType;
  private float speed;
  private float longitude;
  private float latitude;
  private Date gdate;

}
