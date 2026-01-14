package com.wander;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import tk.mybatis.spring.annotation.MapperScan;

// 注意这里引入的mapperScan需要是tk的，而不能是传统mybatis的
@MapperScan("com.wander.mapper")
@SpringBootApplication
public class ProviderPaymentApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProviderPaymentApplication.class, args);
    }
}
