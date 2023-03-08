package com.edu.DYC.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.DYC.reggie.entity.ShoppingCart;
import com.edu.DYC.reggie.mapper.ShoppingCartMapper;
import com.edu.DYC.reggie.service.ShoppingCartService;
import org.springframework.stereotype.Service;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */
@Service
public class ShoppingCartServiceImpl extends ServiceImpl<ShoppingCartMapper, ShoppingCart> implements ShoppingCartService {
}
