package com.wander.controller;

import com.wander.dto.PayDTO;
import com.wander.entity.Pay;
import com.wander.service.PayService;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pay")
public class PayController {

    @Resource
    private PayService payService;

    @PostMapping("/add")
    public Integer add(@RequestBody PayDTO pay) {
        return payService.add(pay);
    }

    @DeleteMapping("/del/{id}")
    public Integer delete(@PathVariable("id") Integer id) {
        return payService.delete(id);
    }

    @PutMapping("/update")
    public Integer update(@RequestBody PayDTO pay) {
        return payService.update(pay);
    }

    @GetMapping("/get")
    public Pay getById(@RequestParam("id") Integer id) {
        return payService.getById(id);
    }

    @GetMapping("/all")
    public List<Pay> getAll() {
        return payService.getAll();
    }

}
