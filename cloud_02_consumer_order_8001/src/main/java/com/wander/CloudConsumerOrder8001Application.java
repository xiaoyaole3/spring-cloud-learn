package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudConsumerOrder8001Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudConsumerOrder8001Application.class, args);
    }
}
