package com.wander.service.impl;

import com.mysql.cj.util.TimeUtil;
import com.wander.mapper.AccountMapper;
import com.wander.service.AccountService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class AccountServiceImpl implements AccountService {

    @Resource
    private AccountMapper accountMapper;

    @Override
    public Integer decrease(Long userId, Long money) {
        Integer result = accountMapper.decrease(userId, money);

        log.info("decrease money sleeping, userId is {}, money is {}", userId, money);
        // 模拟超时场景
        timeOut();

        // 模拟异常场景
//        int a = 10/0;
        log.info("decrease money success, money is {}", money);

        return result;
    }

    private void timeOut() {
        try {
            TimeUnit.SECONDS.sleep(65);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
