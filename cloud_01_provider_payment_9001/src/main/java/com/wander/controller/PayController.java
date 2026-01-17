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
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.*;

import java.util.List;

// 开启配置中心配置改变后自动刷新
@RefreshScope
@Tag(name = "支付微服务模块", description = "支付CRUD")
@RestController
@RequestMapping("/pay")
@ResponseBody
public class PayController {

    @Resource
    private PayService payService;

    @Operation(summary = "新增", description = "新增一个支付")
    @PostMapping("/add")
    public ResultData<Integer> add(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        return ResultData.success(payService.add(pay));
    }

    @Operation(summary = "删除", description = "删除一个支付")
    @DeleteMapping("/del/{id}")
    public ResultData<Integer> delete(@PathVariable(value = "id") Integer id) {
        return ResultData.success(payService.delete(id));
    }

    @Operation(summary = "更改", description = "更改一个支付")
    @PutMapping("/update")
    public ResultData<Integer> update(@RequestBody PayDTO payDTO) {
        Pay pay = new Pay();
        BeanUtils.copyProperties(payDTO, pay);
        return ResultData.success(payService.update(pay));
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
    public ResultData<List<PayDTO>> getAll() {
        List<PayDTO> list = payService.getAll()
                .stream()
                .map(o -> {
                    PayDTO payDTO = new PayDTO();
                    BeanUtils.copyProperties(o, payDTO);
                    return payDTO;
                }).toList();
        return ResultData.success(list);
    }

    @Value("${server.port}")
    private String serverPort;

    @Value("${wander.info}")
    private String consulInfo;

    @Operation(summary = "模拟获取Consul中的配置信息")
    @GetMapping("/consul")
    public ResultData<String> getConsulConfig() {
        String result = "Server port = " + serverPort + " and say = " + consulInfo;

        try {
            Thread.sleep(67000);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResultData.success(result);
    }
}
