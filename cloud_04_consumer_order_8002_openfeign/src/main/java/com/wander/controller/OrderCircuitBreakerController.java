package com.wander.controller;

import com.wander.apis.PaymentFeignAPI;
import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@Tag(name = "订单模块CircuitBreaker", description = "订单模块用于测试CircuitBreaker")
@RestController
@RequestMapping("/order/circuit")
public class OrderCircuitBreakerController {

    @Resource
    private PaymentFeignAPI paymentFeignAPI;

    // 这里配置的name与配置文件中配置的instances相同，为断路器的名称，决定使用哪个断路器
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "fallbackMethod")
    @GetMapping("/test")
    public ResultData<String> circuitBreaker(@RequestParam(value = "id", defaultValue = "100") Integer id) {
        return paymentFeignAPI.testCircuitBreaker(id);
    }

    // fallback方法，注意参数一定要为Throwable
    // 这里最好要有原本的参数，增加Throwable参数
    public ResultData<String> fallbackMethod(Integer id, Throwable throwable) {
        return ResultData.warn("请稍后重试..., id =" + id +
                "\n, throwable message =" + throwable.getMessage() +
                "\n, cause =" + throwable.getCause());
    }
}
