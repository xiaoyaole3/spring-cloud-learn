package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

// 服务的注册和发现
@EnableDiscoveryClient
@SpringBootApplication
public class CloudGateway9527Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudGateway9527Application.class, args);
    }
}
