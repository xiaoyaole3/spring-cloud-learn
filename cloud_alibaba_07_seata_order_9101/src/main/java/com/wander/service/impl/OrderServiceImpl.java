package com.wander.service.impl;

import com.wander.apis.AccountFeignApi;
import com.wander.apis.StorageFeignApi;
import com.wander.entity.Order;
import com.wander.mapper.OrderMapper;
import com.wander.service.OrderService;
import io.seata.core.context.RootContext;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    public Integer create(Order order) {
        // XID 全局事务ID的检查，重要
        String xid = RootContext.getXID();
        log.info("Start to order , the xid is {}", xid);

        // 1. 新建订单
        order.setStatus(0);
        int insert = orderMapper.insert(order);
        // 插入订单后获得插入mysql的实体对象

        if (insert > 0) {
            // 从mysql中查询出插入的记录
            Order orderFromDB = orderMapper.selectOne(order);
            log.info("Order id is {}, info = {}", orderFromDB.getId(), orderFromDB.toString());

            // 2. 扣减库存
            storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            log.info("Storage decreased. product id is {}, count is {}", orderFromDB.getProductId(), orderFromDB.getCount());

            // 3. 扣减账户余额
            accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            log.info("Account decreased. user id is {}", orderFromDB.getUserId());

            // 4. 修改订单状态 -- 用pk更合适
            orderFromDB.setStatus(1);

            // 学习criteria语法
            Example.Criteria criteria = new Example(Order.class)
                    .createCriteria()
                    .andEqualTo("userId", orderFromDB.getUserId())
                    .andEqualTo("productId", orderFromDB.getProductId())
                    .andEqualTo("status", 9);
            int updated = orderMapper.updateByExampleSelective(orderFromDB, criteria);
            if (updated > 0) {
                log.info("Update order success,  id is {}", orderFromDB.getId());
            }
        }

        log.info("End to order , the xid is {}", xid);
        return insert;
    }
}
