package com.edu.DYC.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.DYC.reggie.entity.OrderDetail;
import com.edu.DYC.reggie.mapper.OrderDetailMapper;
import com.edu.DYC.reggie.service.OrderDetailService;
import org.springframework.stereotype.Service;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */
@Service
public class OrderDetailServiceImpl extends ServiceImpl<OrderDetailMapper, OrderDetail> implements OrderDetailService {
}
