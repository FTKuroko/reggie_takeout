package com.edu.DYC.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.DYC.reggie.entity.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
}
