package com.edu.DYC.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.DYC.reggie.entity.Orders;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */
@Mapper
public interface OrderMapper extends BaseMapper<Orders> {
}
