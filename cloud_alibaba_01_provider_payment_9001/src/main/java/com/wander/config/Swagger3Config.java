package com.wander.config;

import io.swagger.v3.oas.models.ExternalDocumentation;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springdoc.core.models.GroupedOpenApi;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class Swagger3Config {

    @Bean
    public GroupedOpenApi paymentApi() {
        return GroupedOpenApi.builder()
                .group("支付模块")
                .pathsToMatch("/pay/**")
                .build();
    }

    @Order(200)
    @Bean
    public GroupedOpenApi otherApi() {
        return GroupedOpenApi.builder()
                .group("其它模块")
                .pathsToMatch("/other/**")
                .build();
    }

    @Bean
    public OpenAPI docsOpenAPI() {
        return new OpenAPI()
                .info(new Info().title("cloud-wander-2023")
                        .description("wander cloud")
                        .version("v1.0"))
                .externalDocs(new ExternalDocumentation()
                        .description("www.wander.press")
                        .url("https://www.wander.press/"));
    }
}
