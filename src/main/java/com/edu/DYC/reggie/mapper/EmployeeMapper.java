package com.edu.DYC.reggie.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.edu.DYC.reggie.entity.Employee;
import org.apache.ibatis.annotations.Mapper;

/**
 * @author :   Kuroko
 * @date :     2023/2/23
 */
@Mapper
public interface EmployeeMapper extends BaseMapper<Employee> {
}
