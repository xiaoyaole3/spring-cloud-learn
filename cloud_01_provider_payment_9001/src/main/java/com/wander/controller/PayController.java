package com.wander.controller;

import com.wander.dto.PayDTO;
import com.wander.entity.Pay;
import com.wander.service.PayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(name = "支付微服务模块", description = "支付CRUD")
@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;

    @Operation(summary = "新增", description = "新增一个订单")
    @PostMapping("/add")
    public Integer add(@RequestBody PayDTO pay) {
        return payService.add(pay);
    }

    @Operation(summary = "删除", description = "删除一个订单")
    @DeleteMapping("/del/{id}")
    public Integer delete(@PathVariable(value = "id") Integer id) {
        return payService.delete(id);
    }

    @Operation(summary = "更改", description = "更改一个订单")
    @PutMapping("/update")
    public Integer update(@RequestBody PayDTO pay) {
        return payService.update(pay);
    }

    @Operation(summary = "获取", description = "获取单个订单")
    @GetMapping("/get")
    public Pay getById(@RequestParam(value = "id", required = true) Integer id) {
        return payService.getById(id);
    }

    @Operation(summary = "获取全部", description = "获取全部订单")
    @GetMapping("/all")
    public List<Pay> getAll() {
        return payService.getAll();
    }

}
