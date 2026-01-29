package com.wander.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wander.entity.Pay;
import com.wander.service.PayService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/pay/feign")
public class PayFeignController {
    
    @Resource
    private PayService payService;

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/info")
    public ResultData<String> getInfo() {
        return ResultData.success("currentNode info = " + port + " " + UUID.randomUUID().toString());
    }

    @SentinelResource(value = "getPayByOrderNo", blockHandler = "payFeignBlockHandler")
    @GetMapping("/orderNo")
    public ResultData getPayByOrderNo(@RequestParam(name = "orderNo") String orderNo) {
        List<Pay> all = payService.getAll();
        for (Pay pay : all) {
            if (pay.getOrderNo().equals(orderNo)) {
                return ResultData.success(pay);
            }
        }
        return ResultData.error("Not get the pay by orderNo" + 1/0);
    }

    public ResultData payFeignBlockHandler(@RequestParam(name = "orderNo") String orderNo, BlockException exception) {
        return ResultData.error("error pay FeignBlockHandler，违反了Sentinel中配置的流控规则");
    }

    // 不再针对每个接口配置Fallback，而是采用在Feign接口中统一配置的方法
//    public ResultData payFeignFallbackHandler(@RequestParam(name = "orderNo") String orderNo, Throwable throwable) {
//        return ResultData.error("系统中执行发生了运行时错误。");
//    }
}
