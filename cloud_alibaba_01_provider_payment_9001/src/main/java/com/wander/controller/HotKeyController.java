package com.wander.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.wander.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hotkey")
public class HotKeyController {

    // 配置goods参数，只要QPS>1，马上降级处理
    @SentinelResource(value = "hotkey", blockHandler = "hotKeyBlockHandler")
    @GetMapping("/test")
    public ResultData<String> testHotKey(
            @RequestParam(value = "goods", required = false) String goods,
            @RequestParam(value = "id", required = false) String id) {

        return ResultData.success("test HotKey");
    }

    public ResultData<String> hotKeyBlockHandler(
            @RequestParam(value = "goods", required = false) String goods,
            @RequestParam(value = "id", required = false) String id,
            BlockException exception) {
        return ResultData.error("test HotKey Handler");
    }
}
