package com.wander.mapper;

import com.wander.entity.Storage;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface StorageMapper extends Mapper<Storage> {

    int decrease(@Param("productId") Long productId,@Param("count") Integer count);
}