package com.edu.DYC.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.DYC.reggie.entity.Employee;
import com.edu.DYC.reggie.mapper.EmployeeMapper;
import com.edu.DYC.reggie.service.EmployeeService;
import org.springframework.stereotype.Service;

/**
 * @author :   Kuroko
 * @date :     2023/2/23
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

}
