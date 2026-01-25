package com.wander.controller;

import com.wander.dto.PayDTO;
import com.wander.entity.Pay;
import com.wander.service.PayService;
import com.wander.vo.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Tag(name = "支付微服务模块", description = "支付CRUD")
@RestController
@RequestMapping("/pay")
@ResponseBody
public class PayController {

    @Resource
    private PayService payService;

    @Operation(summary = "新增", description = "新增一个支付")
    @PostMapping("/add")
    public ResultData<Integer> add(@RequestBody PayDTO pay) {
        Pay payInsert = new Pay();
        BeanUtils.copyProperties(pay, payInsert);
        return ResultData.success(payService.add(payInsert));
    }

    @Operation(summary = "删除", description = "删除一个支付")
    @DeleteMapping("/del/{id}")
    public ResultData<Integer> delete(@PathVariable(value = "id") Integer id) {
        return ResultData.success(payService.delete(id));
    }

    @Operation(summary = "更改", description = "更改一个支付")
    @PutMapping("/update")
    public ResultData<Integer> update(@RequestBody PayDTO pay) {
        Pay payUpdate = new Pay();
        BeanUtils.copyProperties(pay, payUpdate);
        return ResultData.success(payService.update(payUpdate));
    }

    @Operation(summary = "获取", description = "获取单个支付")
    @GetMapping("/get")
    public ResultData<PayDTO> getById(@RequestParam(value = "id", required = true) Integer id) {
        Pay byId = payService.getById(id);
        PayDTO result = new PayDTO();
        BeanUtils.copyProperties(byId, result);
        return ResultData.success(result);
    }

    @Operation(summary = "获取全部", description = "获取全部支付")
    @GetMapping("/all")
    public ResultData<List<Pay>> getAll() {
        return ResultData.success(payService.getAll());
    }

    @Value("${server.port}")
    private Integer port;

    @GetMapping("/info")
    public ResultData<String> getInfo() {
        return ResultData.success("currentNode info = " + port + " " + UUID.randomUUID().toString());
    }
}
