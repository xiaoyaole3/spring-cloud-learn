package com.wander.controller;

import com.wander.vo.ResultData;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/pay")
@ResponseBody
public class PayController {

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/info")
    public ResultData<String> getInfo() {
        return ResultData.success("currentNode info = " + port + " " + UUID.randomUUID().toString());
    }
}
