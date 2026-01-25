package com.wander.filters;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class MyCustomGlobalFilter implements GlobalFilter, Ordered {

    private static final String VISIT_TIME_MARK = "VISIT_TIME";

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        System.out.println("MyCustomGlobalFilter get in");

        // 1. 记录访问接口的开始时间
        exchange.getAttributes().put(VISIT_TIME_MARK, System.currentTimeMillis());
        // 2. 返回统计的记录给后台
        return chain.filter(exchange)
                // 响应式编程允许返回后新启动一个线程完成一些工作
                .then(Mono.fromRunnable(() -> {
                    Long attribute = (Long) exchange.getAttribute(VISIT_TIME_MARK);
                    long currentTimeMillis = System.currentTimeMillis();
                    long realCostTime = currentTimeMillis - attribute;
                    System.out.println("call the method(" + exchange.getRequest().getURI() +") cost time = " + realCostTime);
                }));

    }

    // 数字越小，优先级越高
    @Override
    public int getOrder() {
        return 0;
    }
}
