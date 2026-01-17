package com.wander.apis;

import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "cloud-provider-payment")
public interface PaymentFeignAPI {

    @PostMapping("/pay/add")
    ResultData<Integer> add(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get")
    ResultData<PayDTO> getById(@RequestParam(value = "id", required = true) Integer id);


    @GetMapping("/pay/consul")
    ResultData<String> getConsulConfig();
}
