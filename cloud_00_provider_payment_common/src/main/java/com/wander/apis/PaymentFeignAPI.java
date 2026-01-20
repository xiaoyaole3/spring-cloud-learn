package com.wander.apis;

import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@FeignClient(value = "cloud-provider-payment")
public interface PaymentFeignAPI {

    @PostMapping("/pay/add")
    ResultData<Integer> add(@RequestBody PayDTO payDTO);

    @DeleteMapping("/pay/del/{id}")
    ResultData<Integer> delete(@PathVariable(value = "id") Integer id);

    @PutMapping("/pay/update")
    ResultData<Integer> update(@RequestBody PayDTO payDTO);

    @GetMapping("/pay/get")
    ResultData<PayDTO> getById(@RequestParam(value = "id", required = true) Integer id);

    @GetMapping("/pay/all")
    ResultData<List<PayDTO>> getAll();

    @GetMapping("/pay/consul")
    ResultData<String> getConsulConfig();

    // 注意这里一个Feign代表一个服务会比较合适
    @GetMapping("/circuit/test")
    ResultData<String> testCircuitBreaker(@RequestParam(value = "id", defaultValue = "100") Integer id);

    @GetMapping("/circuit/bulkhead")
    ResultData<String> testBulkHead(@RequestParam("id") Integer id);

    @GetMapping("/circuit/rateLimit")
    ResultData<String> testRateLimit(@RequestParam("id") Integer id);
}
