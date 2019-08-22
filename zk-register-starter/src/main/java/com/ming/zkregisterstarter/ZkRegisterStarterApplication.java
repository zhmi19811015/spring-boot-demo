package com.ming.zkregisterstarter;

import cn.hutool.core.collection.CollUtil;
import com.ming.zkregisterstarter.util.balanc.LoadBalanc;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@SpringBootApplication
@RestController
public class ZkRegisterStarterApplication {
    @Autowired
    private LoadBalanc loadBalanc;

    public static void main(String[] args) {
        SpringApplication.run(ZkRegisterStarterApplication.class, args);
    }


    @GetMapping("/test")
    public String test(){
        String[] col= new String[]{"192.168.10.23:9090"};
        List<String> colList = CollUtil.newArrayList(col);
        return loadBalanc.selectHost(colList);
    }

}
