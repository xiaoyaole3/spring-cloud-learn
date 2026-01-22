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

@Tag(name = "订单监控模块", description = "用于跟踪订单监控")
@RestController
@RequestMapping("/order/feign/micrometer")
public class OrderMicrometerController {

    @Resource
    private PaymentFeignAPI paymentFeignAPI;

    @GetMapping("/hello")
    public ResultData<String> micrometerHello(@RequestParam(value = "id") Integer id) {
        return paymentFeignAPI.payMicrometer(id);
    }
}
