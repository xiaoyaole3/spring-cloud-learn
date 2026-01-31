package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import tk.mybatis.spring.annotation.MapperScan;

@MapperScan("com.wander.mapper") // 注意这里是tk.mybatis，这个与使用的代码生成插件有关
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class SAASeataOrder9101Application {
    public static void main(String[] args) {
        SpringApplication.run(SAASeataOrder9101Application.class, args);
    }
}
