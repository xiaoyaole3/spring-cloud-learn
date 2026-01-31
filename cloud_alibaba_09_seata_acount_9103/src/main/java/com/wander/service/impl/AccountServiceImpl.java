package com.wander.service.impl;

import com.wander.mapper.AccountMapper;
import com.wander.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Integer decrease(Long userId, Long money) {
        return accountMapper.decrease(userId, money);
    }
}
