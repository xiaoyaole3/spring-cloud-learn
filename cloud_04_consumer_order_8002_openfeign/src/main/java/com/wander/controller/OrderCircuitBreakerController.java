package com.wander.controller;

import com.wander.apis.PaymentFeignAPI;
import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Tag(name = "订单模块CircuitBreaker", description = "订单模块用于测试CircuitBreaker")
@RestController
@RequestMapping("/order/circuit")
public class OrderCircuitBreakerController {

    @Resource
    private PaymentFeignAPI paymentFeignAPI;

    @GetMapping("/test")
    public ResultData<String> circuitBreaker(@RequestParam(value = "id", defaultValue = "100") Integer id) {
        return paymentFeignAPI.testCircuitBreaker(id);
    }
}
