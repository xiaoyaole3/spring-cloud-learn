package com.wander.controller;

import com.wander.dto.PayDTO;
import com.wander.vo.ResultData;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.annotation.Resource;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Tag(name = "订单模块", description = "订单用于调用支付模块")
@RestController
@RequestMapping("/order")
public class OrderController {

//    private static final String PAYMENT_URL = "http://localhost:9001/pay";
    // 使用服务注册中心注册的服务名称
    // 如果只修改这里为注册中心的地址，会提示 ： java.net.UnknownHostException: cloud-provider-payment
    // 这是因为Consul默认是带负载均衡的，因此在使用restTemplate的时候需要在容器注入时加 @LoadBalanced 注解
    private static final String PAYMENT_URL = "http://cloud-provider-payment/pay";

    @Resource
    private RestTemplate restTemplate;

    @GetMapping("/add")
    public ResultData addOrder(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_URL + "/add", payDTO, ResultData.class);
    }

    @Operation(summary = "删除", description = "删除一个订单")
    @DeleteMapping("/del/{id}")
    public ResultData delete(@PathVariable(value = "id") Integer id) {
        restTemplate.delete(PAYMENT_URL + "/del/" + id);
        return ResultData.success("null");
    }

    @Operation(summary = "更改", description = "更改一个订单")
    @PutMapping("/update")
    public ResultData<Integer> update(@RequestBody PayDTO payDTO) {
        return restTemplate.postForObject(PAYMENT_URL + "/update", payDTO, ResultData.class);
    }

    @Operation(summary = "获取", description = "获取单个订单")
    @GetMapping("/get")
    public ResultData<PayDTO> getById(@RequestParam(value = "id", required = true) Integer id) {
        return restTemplate.getForObject(PAYMENT_URL + "/get?id=" + id, ResultData.class);
    }

    @Operation(summary = "获取全部", description = "获取全部订单")
    @GetMapping("/all")
    public ResultData<List<PayDTO>> getAll() {
        return restTemplate.getForObject(PAYMENT_URL + "/all", ResultData.class);
    }

    // 默认情况下是通过Ribbon的方式负载
    @Operation(summary = "order中模拟获取Consul中的配置信息", description = "注意这里是使用的客户端")
    @GetMapping("/consul")
    public ResultData<String> getConsulConfig() {
        ResultData resultData = restTemplate.getForObject(PAYMENT_URL + "/consul", ResultData.class);
        String data = (String) resultData.getData();
        return ResultData.success(data);
    }

    @Resource
    private DiscoveryClient discoveryClient;

    @Operation(summary = "通过DiscoveryClient查询注册到Consul中的信息")
    @GetMapping("/discovery")
    public void discovery() {
        List<String> services = discoveryClient.getServices();
        System.out.println("all services : " + services);
        List<ServiceInstance> instances = discoveryClient.getInstances("cloud-provider-payment");
        for (ServiceInstance instance : instances) {
            String serviceId = instance.getServiceId();
            String host = instance.getHost();
            int port = instance.getPort();
            System.out.println("serviceId : " + serviceId + " host : " + host + " port : " + port);
        }
    }
}
