package com.wander.controller;

import com.wander.apis.PaymentFeignAPI;
import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Tag(name = "订单模块", description = "订单用于调用支付模块")
@RestController
@RequestMapping("/order/feign")
public class OrderController {

    @Resource
    private PaymentFeignAPI paymentFeignAPI;

    @PostMapping("/add")
    public ResultData<Integer> addOrder(@RequestBody PayDTO payDTO) {
        return paymentFeignAPI.add(payDTO);
    }

    @Operation(summary = "删除", description = "删除一个订单")
    @DeleteMapping("/del/{id}")
    public ResultData<Integer> delete(@PathVariable(value = "id") Integer id) {
        return paymentFeignAPI.delete(id);
    }

    @Operation(summary = "更改", description = "更改一个订单")
    @PutMapping("/update")
    public ResultData<Integer> update(@RequestBody PayDTO payDTO) {
        return paymentFeignAPI.update(payDTO);
    }

    @Operation(summary = "获取", description = "获取单个订单")
    @GetMapping("/get")
    public ResultData<PayDTO> getById(@RequestParam(value = "id", required = true) Integer id) {
        return paymentFeignAPI.getById(id);
    }

    @Operation(summary = "获取全部", description = "获取全部订单")
    @GetMapping("/all")
    public ResultData<List<PayDTO>> getAll() {
        return paymentFeignAPI.getAll();
    }

    // 默认情况下是通过Ribbon的方式负载
    @Operation(summary = "order中模拟获取Consul中的配置信息", description = "注意这里是使用的客户端")
    @GetMapping("/consul")
    public ResultData<String> getConsulConfig() {
        try {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            return paymentFeignAPI.getConsulConfig();
        } catch (Exception e) {
            System.out.println(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()));
            e.printStackTrace();
        }
        return null;
    }
}
