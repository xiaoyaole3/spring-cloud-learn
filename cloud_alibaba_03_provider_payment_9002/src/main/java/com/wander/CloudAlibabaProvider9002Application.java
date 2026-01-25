package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication
public class CloudAlibabaProvider9002Application {
    public static void main(String[] args) {
        SpringApplication.run(CloudAlibabaProvider9002Application.class, args);
    }
}
