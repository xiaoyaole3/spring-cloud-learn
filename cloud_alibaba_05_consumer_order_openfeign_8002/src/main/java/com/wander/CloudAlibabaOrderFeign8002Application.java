package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

// 记得要开启Feign的功能
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaOrderFeign8002Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaOrderFeign8002Application.class, args);
    }
}
