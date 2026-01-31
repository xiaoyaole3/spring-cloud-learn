package com.wander.controller;

import com.wander.service.StorageService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/storage")
public class StorageController {

    @Resource
    private StorageService storageService;

    @PostMapping("/decrease")
    public ResultData decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count) {
        Integer decrease = storageService.decrease(productId, count);
        return ResultData.success(decrease);
    }
}
