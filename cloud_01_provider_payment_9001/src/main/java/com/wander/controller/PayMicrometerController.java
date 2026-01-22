package com.wander.controller;

import com.wander.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequestMapping("/micrometer")
public class PayMicrometerController {

    @GetMapping("/hello")
    public ResultData<String> payMicrometer(@RequestParam(name = "id") Integer id) {
        return ResultData.success("Hello Micrometer id(" + id  + ")" + UUID.randomUUID().toString() );
    }
}
