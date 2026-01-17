package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import tk.mybatis.spring.annotation.MapperScan;


@EnableDiscoveryClient
// 注意这里引入的mapperScan需要是tk的，而不能是传统mybatis的
@MapperScan("com.wander.mapper")
@SpringBootApplication
public class ProviderPayment9001Application {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPayment9001Application.class, args);
    }
}
