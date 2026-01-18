package com.wander.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@RestController
@RequestMapping(value = "/circuit")
public class PayCircuitBreakerController {

    @GetMapping("/test")
    public String testCircuitBreaker(@RequestParam(value = "id", defaultValue = "100") Integer id) {
        if (id == -4) {
            throw new RuntimeException("circuit breaker failed, not  -4");
        }
        if (id == 9999) {
            try {
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        return "success:[" + id + "]-" + UUID.randomUUID().toString();
    }

}
