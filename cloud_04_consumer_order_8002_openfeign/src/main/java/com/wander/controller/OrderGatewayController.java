package com.wander.controller;

import com.wander.apis.PaymentFeignAPI;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/gateway")
public class OrderGatewayController {

    @Resource
    private PaymentFeignAPI paymentFeignAPI;

    @GetMapping("/info")
    public ResultData<String> getGatewayInfo() {
        return paymentFeignAPI.getGatewayInfo();
    }
}
