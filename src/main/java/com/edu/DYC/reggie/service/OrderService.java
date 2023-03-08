package com.edu.DYC.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.DYC.reggie.entity.Orders;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */
public interface OrderService extends IService<Orders> {
    public void submit(Orders orders);
}
