package com.wander.config;

import feign.Logger;
import feign.Retryer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.TimeUnit;

@Configuration
public class OpenFeignConfig {

    @Bean
    public Retryer retryer() {
//        return Retryer.NEVER_RETRY; // 默认情况

        // 最大请求次数为3， 初始间隔为100ms，重试最大间隔时间为1s
        return new Retryer.Default(100, TimeUnit.SECONDS.toMillis(1L),3);
    }

    // 开启打印全部日志级别
    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.FULL;
    }
}
