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


# 项目工程说明

## cloud_00_common
整个工程的公共类包，里面定义了工程里面公用的返回类型包装以及枚举等。

> ❗️要注意学习ResultData中实现的构造器模式，通过静态内部类实现。

## cloud_01_provider_payment_9001

服务端 `payment` 工程，用于连接数据库提供服务。

在配置文件中提供了连接Consul相关配置信息。

## cloud_02_consumer_order_8001

服务端 `order` 工程，用于通过 `RestTemplate` 通过注册中心访问服务端。

## cloud_03_provider_payment_9002

服务端 `payment` 工程，等同于 `cloud_01_provider_payment_9001`， 用来表示同一个服务的不同实例。
