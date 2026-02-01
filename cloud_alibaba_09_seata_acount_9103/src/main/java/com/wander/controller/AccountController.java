package com.wander.controller;

import com.wander.service.AccountService;
import com.wander.vo.ResultData;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/account")
public class AccountController {

    @Resource
    private AccountService accountService;

    @PostMapping("/decrease")
    ResultData decrease(@RequestParam("userId") Long userId, @RequestParam("money") Long money){
        Integer decrease = accountService.decrease(userId, money);
        return ResultData.success(decrease);
    }
}
