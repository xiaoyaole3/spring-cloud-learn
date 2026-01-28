package com.wander.controller;

import com.wander.vo.ResultData;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/grant")
public class GrantController {

    @GetMapping("/grant")
    public ResultData<String> grant() {
        return ResultData.success("grant success");
    }
}
