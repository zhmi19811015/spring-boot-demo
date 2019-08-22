package com.ming.springbootconditional;

import com.ming.springbootconditional.service.CmdService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringbootConditionalApplication {

    public static void main(String[] args) {
       // System.out.println("Mac OS X".contains("Mac"));

        ApplicationContext ctx = SpringApplication.run(SpringbootConditionalApplication.class, args);
        CmdService cmdService = ctx.getBean(CmdService.class);
        cmdService.print();
    }

}
