package com.ming.zkregisterstarter.util.balanc;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;

/**
 * 随机负载算法
 *
 * @author zhangming
 * @version 1.0
 * @date 2019-08-21 20:19
 */
@Service
public class RandomLoadBalanc extends AbstractLoadBalanc {

    @Override
    protected String doSelect(List<String> resp) {
        int length = resp.size();
        Random random = new Random();
        return resp.get(random.nextInt(length));
    }
}
