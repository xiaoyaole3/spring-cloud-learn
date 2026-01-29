package com.wander.fallback;

import com.wander.apis.PaymentFeignApi;
import com.wander.vo.ResultData;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignFallback implements PaymentFeignApi {
    @Override
    public ResultData<String> getInfo() {
        return ResultData.error("get Info error");
    }

    @Override
    public ResultData getPayByOrderNo(String orderNo) {
        return ResultData.error("get PayByOrderNo error");
    }
}
