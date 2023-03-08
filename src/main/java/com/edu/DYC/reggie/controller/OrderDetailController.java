package com.edu.DYC.reggie.controller;

import com.edu.DYC.reggie.service.OrderDetailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */

/**
 * 订单明细
 */
@Slf4j
@RequestMapping("/orderDetail")
@RestController
public class OrderDetailController {
    @Autowired
    private OrderDetailService orderDetailService;
}
