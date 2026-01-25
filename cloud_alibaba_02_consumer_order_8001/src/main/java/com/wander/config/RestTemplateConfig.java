package com.wander.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @LoadBalanced // 赋予restTemplate负载均衡的能力
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
