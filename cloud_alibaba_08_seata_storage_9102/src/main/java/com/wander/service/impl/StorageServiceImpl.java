package com.wander.service.impl;

import com.wander.mapper.StorageMapper;
import com.wander.service.StorageService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class StorageServiceImpl implements StorageService {

    @Resource
    private StorageMapper storageMapper;

    @Override
    public Integer decrease(Long productId, Integer count) {
        log.info("decrease storage, productId:{}, count:{}", productId, count);
        return storageMapper.decrease(productId, count);
    }
}
