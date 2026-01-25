package com.wander.controller;

import com.wander.dto.PayDTO;
import com.wander.entity.Pay;
import com.wander.service.PayService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.util.Enumeration;
import java.util.HexFormat;
import java.util.UUID;

@RestController
@RequestMapping("/gateway")
public class PayGatewayController {

    @Resource
    private PayService payService;

    @GetMapping("/get")
    public ResultData<PayDTO> gatewayGetById(@RequestParam(value = "id") Integer id) {
        Pay byId = payService.getById(id);
        PayDTO payDTO = new PayDTO();
        BeanUtils.copyProperties(byId,payDTO);
        return ResultData.success(payDTO);
    }

    @GetMapping("/info")
    public ResultData<String> getGatewayInfo() {
        return ResultData.success("Gateway info : " + UUID.randomUUID().toString());
    }

    @GetMapping("/filter")
    public ResultData<String> getGatewayFilter(HttpServletRequest request, HttpServletResponse response) {
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
        return ResultData.success("Gateway filter result : " + result + " " + Instant.now().toString());
    }
}
