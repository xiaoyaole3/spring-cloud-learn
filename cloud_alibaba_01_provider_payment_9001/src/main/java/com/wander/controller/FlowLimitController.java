package com.wander.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.wander.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/flow/limit")
public class FlowLimitController {

    @GetMapping("/testA")
    public ResultData<String> testA() {
        return ResultData.success("testA");
    }
    @GetMapping("/testB")
    @SentinelResource("wanderB")
    public ResultData<String> testB() {
        return ResultData.success("testA");
    }
}
