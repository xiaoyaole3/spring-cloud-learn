package com.wander.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wander.service.SentinelFlowLimitService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/sentinel/resource")
public class SentinelResourceController {

    @Resource
    private SentinelFlowLimitService sentinelFlowLimitService;

    @GetMapping("/byUrl")
    public ResultData<String> defaultFunc() {
        return ResultData.success("default");
    }

    @SentinelResource(value = "bySentinelResource", blockHandler = "wanderBlockHandler", fallback = "wanderFallbackHandler")
    @GetMapping("/byResource")
    public ResultData<String> resourceFunc(@RequestParam("id") Integer id) {
        if (id == 1) {
            throw new RuntimeException("p1 直接异常");
        }
        return ResultData.success("resource");
    }

    // 当被流控之后，返回的自定义限流提示
    public ResultData<String> wanderBlockHandler(@RequestParam("id") Integer id, BlockException ex) {
        return ResultData.error("wander's block,使用 @SentinelResource注解");
    }

    // 当程序异常之后，兜底函数
    public ResultData<String> wanderFallbackHandler(@RequestParam("id") Integer id, Throwable e) {
        return ResultData.error("wander's fallback exception,使用 @SentinelResource注解" + e.getMessage());
    }

}
