package com.ming;

import com.ming.mingspringbootstarter.service.MingService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@SpringBootApplication
@EnableScheduling
@Slf4j
public class SpringbootLogbackApplication extends SpringBootServletInitializer {

    @Autowired
    private MingService mingService;

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
        return builder.sources(SpringbootLogbackApplication.class);
    }


    public static void main(String[] args) {
        SpringApplication.run(SpringbootLogbackApplication.class, args);
    }



    /**
     * 获取配置文件中的属性值
     */
    @Value("${app.name}")
    private String projectName;

    @RequestMapping("/index")
    public String index() {

        /*
            logback.xml中指定的默认输出级别为INFO，所以低于INFO级别(DEBUG、TRACE)的日志不会输出
            级别排序为： TRACE < DEBUG < INFO < WARN < ERROR
         */
        //Ctrl + Alrt + t
        //<editor-fold desc="折叠测试">
        log.info("{} -- This is a primary with logback., Current time {}.", projectName, new Date());
        log.trace("This level is TRACE.");
        log.debug("This level is DEBUG.");
        log.debug("This level is DEBUG.", log.isDebugEnabled());
        log.info("This level is INFO.");
        log.info("isInfoEnabled:" + log.isInfoEnabled());
        log.warn("This level is WARN.");
        log.error("This level is ERROR.");
        return "logback.";
        //</editor-fold>
    }


    @GetMapping("/starter")
    public String starter(String str){
        return mingService.wrap(str);
    }

}
