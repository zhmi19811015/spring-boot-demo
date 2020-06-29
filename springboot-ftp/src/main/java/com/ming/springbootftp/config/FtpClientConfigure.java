package com.ming.springbootftp.config;

import com.ming.springbootftp.core.FtpClientFactory;
import com.ming.springbootftp.core.FtpClientTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FTPClient配置类，封装了FTPClient的相关配置
 *
 * @author zhangming
 * @version 1.0
 * @date 2020/6/4 7:48 下午
 */
@Configuration
@EnableConfigurationProperties(FtpClientProperties.class)
public class FtpClientConfigure {
    private FtpClientProperties ftpClientProperties;

    @Autowired
    public void setFtpClientProperties(FtpClientProperties ftpClientProperties) {
        this.ftpClientProperties = ftpClientProperties;
    }

    @Bean
    public FtpClientFactory getFtpClientFactory() {
        return new FtpClientFactory(ftpClientProperties);
    }

    @Bean
    public FtpClientTemplate getFtpTemplate() {
        return new FtpClientTemplate(getFtpClientFactory());
    }

}
