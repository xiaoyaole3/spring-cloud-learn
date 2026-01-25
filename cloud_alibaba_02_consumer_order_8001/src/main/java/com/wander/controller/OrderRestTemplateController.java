package com.wander.controller;

import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/restTemplate")
public class OrderRestTemplateController {

    @Resource
    private RestTemplate restTemplate;
    @Resource
    private LoadBalancerClient loadBalancerClient;

    @Value("${service-url.nacos-user-service}")
    private String nacosUserService;

    @GetMapping("/info")
    public ResultData<String> getProviderInfo() {
        ResultData resultData = restTemplate.getForObject(nacosUserService + "/pay/info", ResultData.class);
        System.out.printf("result Data : ", resultData);
        return resultData;
    }
}
