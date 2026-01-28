package com.wander.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wander.service.SentinelFlowLimitService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow/limit")
public class FlowLimitController {

    @Resource
    private SentinelFlowLimitService sentinelFlowLimitService;

    @GetMapping("/testA")
    public ResultData<String> testA() {
        return ResultData.success("testA");
    }
    @GetMapping("/testB")
    @SentinelResource("wanderB")
    public ResultData<String> testB() {
        return ResultData.success("testA");
    }

    /**
     * 流控链路演示，C、D两个请求都访问common方法
     */

    @GetMapping("/testC")
    public ResultData<String> testC() {
        sentinelFlowLimitService.common();
        return ResultData.success("testC");
    }
    @GetMapping("/testD")
    public ResultData<String> testD() {
        sentinelFlowLimitService.common();
        return ResultData.success("testD");
    }

    @GetMapping("/testF")
    public ResultData<String> testF() {
        sentinelFlowLimitService.common();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return ResultData.success("testF");
    }
}
