package com.wander.controller;


import com.wander.vo.ResultData;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.time.Instant;
import java.util.Enumeration;


@RestController
@RequestMapping("/pay/prefix/gateway2")
public class PrefixPathGatewayController {

    @GetMapping("/filter")
    public ResultData<String> getPrefixGatewayFilter(HttpServletRequest request, HttpServletResponse response) {
        StringBuilder result = new StringBuilder();

        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = request.getHeader(headerName);
            System.out.println(headerName + ":" + headerValue);

            if (headerName.matches("x-request-wander.*")) {
                result.append(headerName).append(":").append(headerValue);
            }
        }
        // 添加返回的请求头信息
        response.setHeader("x-response-wander", "aa");

        // 获取请求参数
        String customerId = request.getParameter("customerId");
        String customerName = request.getParameter("customerName");
        System.out.println("customerId:" + customerId + ",customerName:" + customerName);

        return ResultData.success("Gateway filter result : " + result + " " + Instant.now().toString());
    }
}
