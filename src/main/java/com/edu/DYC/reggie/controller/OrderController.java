package com.edu.DYC.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.edu.DYC.reggie.common.R;
import com.edu.DYC.reggie.entity.Orders;
import com.edu.DYC.reggie.service.OrderService;
import com.edu.DYC.reggie.vo.OrdersDto;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */

/**
 * 订单
 */
@Slf4j
@RequestMapping("/order")
@RestController
public class OrderController {
    @Autowired
    private OrderService orderService;

    /**
     * 下单
     * @param orders
     * @return
     */
    @PostMapping("/submit")
    public R<String> submit(@RequestBody Orders orders){
        log.info("订单数据:{}", orders);
        orderService.submit(orders);
        return R.success("下单成功");
    }

    /**
     * 分页查看订单信息
     * @param page
     * @param pageSize
     * @return
     */
    public R<Page> page(int page, int pageSize,String name){
        Page<Orders> pageInfo = new Page<>(page, pageSize);
        Page<OrdersDto> ordersDtoPage = new Page<>();

        BeanUtils.copyProperties(pageInfo, ordersDtoPage);

        LambdaQueryWrapper<Orders> queryWrapper = new LambdaQueryWrapper<>();

        return null;
    }
}
