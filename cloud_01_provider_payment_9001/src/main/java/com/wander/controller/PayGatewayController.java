package com.wander.controller;

import com.wander.dto.PayDTO;
import com.wander.entity.Pay;
import com.wander.service.PayService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
}
