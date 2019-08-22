package com.ming.springbootmybatis.vo;

import lombok.Data;

/**
 * 类作用描述
 *
 * @author zhangming
 * @version 1.0
 * @date 2019/7/17 9:59 AM
 */
@Data
public class PageVo {
    private int pageNum =1;
    private int pageSize = 10;
    /** 排序类型 desc asc.*/
    private String sortType;
    /** 排序字段 .*/
    private String sortField;

}
