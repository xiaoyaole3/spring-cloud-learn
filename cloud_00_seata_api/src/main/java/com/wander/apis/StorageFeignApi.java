package com.wander.apis;

import com.wander.vo.ResultData;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignApi {

    @PostMapping("/storage/decrease")
    ResultData decrease(@RequestParam("productId") Long productId,@RequestParam("count") Integer count);
}
