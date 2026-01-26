package com.wander.service;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import org.springframework.stereotype.Service;

@Service
public class SentinelFlowLimitService {

    @SentinelResource("common")
    public void common() {
        System.out.println("Flow limit common get in ");
    }
}
