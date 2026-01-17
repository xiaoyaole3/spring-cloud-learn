package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 用于向Consul为注册中心时注册服务
@EnableDiscoveryClient
// 开启OpenFeign相关注解
@EnableFeignClients
@SpringBootApplication
public class ConsumerOrderOpenFeign8002Application {
    public static void main(String[] args) {
        SpringApplication.run(ConsumerOrderOpenFeign8002Application.class, args);
    }
}
