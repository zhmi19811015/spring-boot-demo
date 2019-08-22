package com.ming.zkregisterstarter.util.balanc;

import java.util.List;

/**
 * 负载均衡算法接口
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-21 20:12
 */
public interface LoadBalanc {
    String selectHost(List<String> resp);
}
