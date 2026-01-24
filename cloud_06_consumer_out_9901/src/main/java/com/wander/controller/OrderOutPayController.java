package com.wander.controller;

import com.wander.apis.PaymentGatewayFeign;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/out")
public class OrderOutPayController {

    @Resource
    private PaymentGatewayFeign paymentGatewayFeign;

    @GetMapping("/gateway/info")
    public ResultData<String> getGatewayInfo() {
        return paymentGatewayFeign.getGatewayInfo();
    }
}
