package com.wander.apis;

import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-gateway")
public interface PaymentGatewayFeign {

    // Gateway 中配置的两个相关接口
    @GetMapping("/gateway/get")
    ResultData<PayDTO> gatewayGetById(@RequestParam(value = "id") Integer id);

    @GetMapping("/gateway/info")
    ResultData<String> getGatewayInfo();
}
