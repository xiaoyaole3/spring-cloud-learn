package com.wander.predicate;

import jakarta.validation.constraints.NotNull;
import org.springframework.cloud.gateway.handler.predicate.AbstractRoutePredicateFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import java.util.Collections;
import java.util.List;
import java.util.function.Predicate;

// 自定义配置会员等级，根据钻/金/银和yaml中配置的会员等级决定是否能访问
// userType
// 完全参考  AfterRoutePredicateFactory 进行
@Component
public class MyRoutePredicateFactory extends AbstractRoutePredicateFactory<MyRoutePredicateFactory.Config> {

    public MyRoutePredicateFactory() {
        super(MyRoutePredicateFactory.Config.class);
    }

    @Override
    public Predicate<ServerWebExchange> apply(Config config) {
        return new Predicate<ServerWebExchange>() {

            @Override
            public boolean test(ServerWebExchange serverWebExchange) {
                String userType = serverWebExchange.getRequest().getQueryParams().getFirst("userType");
                if (userType == null) {
                    return false;
                }
                // 与 yaml 中配置的一致允许访问
                if (userType.equals(config.getUserType())) {
                    return true;
                }
                return false;
            }
        };
    }

    // 如果不实现下面这个函数，Shortcut配置不生效，只能使用默认的长配置类型
    /**
     * - name: My
     *   args:
     *     userType: diamond
     */
    @Override
    public List<String> shortcutFieldOrder() {
        return Collections.singletonList("userType");
    }

    // 路由断言规则
    public static class Config {
        @NotNull
        private String userType;

        public String getUserType() {
            return userType;
        }

        public void setUserType(String userType) {
            this.userType = userType;
        }
    }
}
