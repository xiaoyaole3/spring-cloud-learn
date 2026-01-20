package com.wander.controller;

import com.wander.apis.PaymentFeignAPI;
import com.wander.vo.ResultData;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

@Tag(name = "订单模块CircuitBreaker", description = "订单模块用于测试CircuitBreaker")
@RestController
@RequestMapping("/order/circuit")
public class OrderCircuitBreakerController {

    @Resource
    private PaymentFeignAPI paymentFeignAPI;

    // 这里配置的name与配置文件中配置的instances相同，为断路器的名称，决定使用哪个断路器
    @CircuitBreaker(name = "cloud-provider-payment", fallbackMethod = "myCircuitBreakerFallback")
    @GetMapping("/test")
    public ResultData<String> circuitBreaker(@RequestParam(value = "id", defaultValue = "100") Integer id) {
        return paymentFeignAPI.testCircuitBreaker(id);
    }

    // fallback方法，注意参数一定要为Throwable
    // 这里最好要有原本的参数，增加Throwable参数
    public ResultData<String> myCircuitBreakerFallback(Integer id, Throwable throwable) {
        return ResultData.warn("请稍后重试..., id =" + id +
                "\n, throwable message =" + throwable.getMessage() +
                "\n, cause =" + throwable.getCause());
    }

    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "myBulkHeaderFallback", type = Bulkhead.Type.SEMAPHORE)
    @GetMapping("/bulkhead/semaphore")
    public ResultData<String> bulkHeadSemaphore(@RequestParam(value = "id", defaultValue = "100") Integer id) {
        return paymentFeignAPI.testBulkHead(id);
    }

    @Bulkhead(name = "cloud-provider-payment", fallbackMethod = "myBulkHeaderFallbackThreadPool", type = Bulkhead.Type.THREADPOOL)
    @GetMapping("/bulkhead/threadPool")
    public CompletableFuture<ResultData<String>> bulkHeadThreadPool(@RequestParam(value = "id", defaultValue = "100") Integer id) {
        System.out.println("Thread name = " + Thread.currentThread().getName());

        try {
            System.out.println(Thread.currentThread().getName() + " in sleep" + "id = " + id);
            TimeUnit.SECONDS.sleep(3);
            System.out.println(Thread.currentThread().getName() + " out sleep" + "id = " + id);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        return CompletableFuture.supplyAsync(() -> paymentFeignAPI.testBulkHead(id));
    }

    public ResultData<String> myBulkHeaderFallback(Integer id, Throwable throwable) {
        return ResultData.warn("Bulk超过了最大限制，请稍后重试..., id =" + id +
                "\n, throwable message =" + throwable.getMessage() +
                "\n, cause =" + throwable.getCause());
    }

    public CompletableFuture<ResultData<String>> myBulkHeaderFallbackThreadPool(Integer id, Throwable throwable) {
        return CompletableFuture.supplyAsync(() -> ResultData.warn("Bulk超过了最大限制，请稍后重试..., id =" + id +
                "\n, throwable message =" + throwable.getMessage() +
                "\n, cause =" + throwable.getCause()));
    }
}
