package com.wander.controller;


import com.wander.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;



@RestController
public class ModifyPathGatewayController {

    @GetMapping("/pay/prefix/gateway2/filter")
    public ResultData<String> getPrefixGatewayFilter(HttpServletRequest request, HttpServletResponse response) {
        return ResultData.success("Gateway filter : " + Instant.now().toString());
    }

    @GetMapping("/pay/path/filter")
    public ResultData<String> getSetPathGatewayFilter(HttpServletRequest request, HttpServletResponse response) {
        return ResultData.success("Gateway filter : " + Instant.now().toString());
    }

    @GetMapping("/pay/gateway2/real/filter")
    public ResultData<String> getRewriteGatewayFilter(HttpServletRequest request, HttpServletResponse response) {
        return ResultData.success("Gateway filter : " + Instant.now().toString());
    }
}
