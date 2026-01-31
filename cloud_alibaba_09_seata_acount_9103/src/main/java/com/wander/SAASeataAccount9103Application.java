package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.wander.mapper")
@EnableDiscoveryClient
@SpringBootApplication
public class SAASeataAccount9103Application {
    public static void main(String[] args) {
        SpringApplication.run(SAASeataAccount9103Application.class, args);
    }
}
