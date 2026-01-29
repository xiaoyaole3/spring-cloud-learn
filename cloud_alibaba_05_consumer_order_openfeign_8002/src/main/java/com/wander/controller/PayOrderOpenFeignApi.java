package com.wander.controller;

import com.wander.apis.PaymentFeignApi;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class PayOrderOpenFeignApi {

    @Resource
    private PaymentFeignApi paymentFeignApi;

    @GetMapping("/orderNo")
    public ResultData getPayByOrderNo(@RequestParam(name = "orderNo") String orderNo) {
        return paymentFeignApi.getPayByOrderNo(orderNo);
    }
}
