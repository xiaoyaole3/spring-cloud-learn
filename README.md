# spring-cloud-2023

该工程是用于学习 SpringCloud 2023 对应版本。参考学习的视频是尚硅谷阳哥视频 ： https://www.bilibili.com/video/BV1gW421P7RD


使用的工具和版本之间有如下关系：

版本决定的参考地址 ： https://sca.aliyun.com/docs/2023/overview/version-explain/?spm=5176.29160081.0.0.74805c72KegJr6
以及 https://spring.io/projects/spring-cloud#learn

- JDK17
- SpringCloud Alibaba 2023.0.1.0
- SpringCloud 2023.0.1
- SpringBoot 3.2.4
- Sentinel 1.8.6
- Nacos 2.3.2
- Seata 2.0.0

## 项目模拟业务场景说明

从 `订单 -> 支付`，逐步引入各个组件。

## 00_payment_api

集成了 payment 对外暴露的 openfeign 接口。

## 00_seata_api

集成了 seata 中 storage 服务和 account 服务对外暴露的 openfeign 接口。

## 01_provider_payment_9001

1. 使用 nacos 进行服务注册发现，提供 payment 先关的能力和服务。
2. 集成了 sentinel 测试服务流控、降级、熔断、热点、参数限流等能力。

## 02_consumer_order_8001

使用 RestTemplate 访问注册到 nacos 注册中新的微服务。

## 03_provider_payment_9002

测试服务提供端提供的负载均衡能力。

## 04_nacos_config_client_3377

用于测试 nacos 作为配置中心的能力，通过 bootstrap.yaml 配置与 nacos 的连接信息。

## 05_consumer_order_openfeign_8002

1. 使用 openFeign 对于 payment 服务进行调用。
2. openFeign 集成了 sentinel，在 Feign 接口处使用全局的 fallback 类来配置接口的 fallback 函数。

## 06_sentinel_gateway_9527

sentinel 结合 gateway 网关，实现对于网关 route 的限流控制。

## 07_seata_order_9101、08_seata_storage_9102、09_seata_account_9103

项目结合 seata 实现分布式事务的管理，通过 `@GlobalTransactional` 注解进行控制。

分布式事务支持下面的几种模式：
- AT模式
- TCC模式
- SAGA模式
- XA模式