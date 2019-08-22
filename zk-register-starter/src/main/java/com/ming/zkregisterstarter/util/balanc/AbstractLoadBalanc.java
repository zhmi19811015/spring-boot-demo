package com.ming.zkregisterstarter.util.balanc;

import java.util.List;

/**
 * 抽象模板
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-21 20:14
 */
public abstract class AbstractLoadBalanc implements LoadBalanc{

    @Override
    public String selectHost(List<String> resp) {
        if (resp ==null || resp.size() == 0){
            return  null;
        }
        if (resp.size() == 1){
            return  resp.get(0);
        }
        return doSelect(resp);
    }

    protected abstract String doSelect(List<String> resp);
}
