package com.wander.mapper;

import com.wander.entity.Account;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

public interface AccountMapper extends Mapper<Account> {

    Integer decrease(@Param("userId") Long userId,@Param("money") Long money);
}