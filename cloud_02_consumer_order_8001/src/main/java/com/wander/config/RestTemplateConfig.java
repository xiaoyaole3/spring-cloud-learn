package com.wander.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    // 注册进入到Consul时需要添加
    @LoadBalanced
    // 向容器中注入一个RestTemplate操作数据库
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
