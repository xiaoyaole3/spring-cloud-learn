package com.wander.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RefreshScope
@RestController
public class NacosConfigClientController {

    @Value("${config.info}")
    private String config;

    @Value("${config.wander}")
    private String wander;

    @GetMapping("/get/nacos/config")
    public String getConfig() {
        return "config:" + config + ",wander:" + wander;
    }
}
