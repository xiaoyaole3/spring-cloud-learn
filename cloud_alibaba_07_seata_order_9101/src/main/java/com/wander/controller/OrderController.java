package com.wander.controller;

import com.wander.entity.Order;
import com.wander.service.OrderService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping("/create")
    public ResultData create(@RequestBody Order order) {
        Integer i = orderService.create(order);
        return ResultData.success(order);
    }
}
