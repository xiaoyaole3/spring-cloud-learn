package com.wander.apis;


import com.wander.fallback.PaymentFeignFallback;
import com.wander.vo.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

// fallback 本质上是在失败的时候调用同名同参或者指定的函数
@FeignClient(name = "cloud-alibaba-provider", fallback = PaymentFeignFallback.class)
public interface PaymentFeignApi {

    @GetMapping("/pay/feign/info")
    ResultData<String> getInfo();

    @GetMapping("/pay/feign/orderNo")
    ResultData getPayByOrderNo(@RequestParam(name = "orderNo") String orderNo);
}
