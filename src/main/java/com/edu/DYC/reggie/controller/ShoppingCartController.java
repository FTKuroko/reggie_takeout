package com.edu.DYC.reggie.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.edu.DYC.reggie.common.BaseContext;
import com.edu.DYC.reggie.common.R;
import com.edu.DYC.reggie.entity.ShoppingCart;
import com.edu.DYC.reggie.service.ShoppingCartService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */
@Slf4j
@RequestMapping("/shoppingCart")
@RestController
public class ShoppingCartController {

    @Autowired
    ShoppingCartService shoppingCartService;

    /**
     * 添加购物车
     * @param shoppingCart
     * @return
     */
    @PostMapping("/add")
    public R<ShoppingCart> add(@RequestBody ShoppingCart shoppingCart){
        log.info("购物车数据:{}", shoppingCart);
        // 设置用户 id
        Long userId = BaseContext.getCurrentId();
        shoppingCart.setUserId(userId);

        // 查询当前菜品或者套餐是否已经在购物车中
        Long dishId = shoppingCart.getDishId();

        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, userId);

        if(dishId != null){
            // 添加到购物车的为菜品
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        }else{
            // 添加到购物车的为套餐
            queryWrapper.eq(ShoppingCart::getSetmealId, shoppingCart.getSetmealId());
        }

        ShoppingCart cart = shoppingCartService.getOne(queryWrapper);

        if(cart != null){
            // 如果已经存在，则在原来的基础上加一
            Integer number = cart.getNumber();
            cart.setNumber(number + 1);
            shoppingCartService.updateById(cart);
        }else{
            // 如果不存在，则添加到购物车中，默认为 1
            shoppingCart.setNumber(1);
            shoppingCart.setCreateTime(LocalDateTime.now());
            shoppingCartService.save(shoppingCart);
            cart = shoppingCart;
        }

        return R.success(cart);
    }

    /**
     * 查看购物车
     * @return
     */
    @GetMapping("/list")
    public R<List<ShoppingCart>> list(){
        log.info("查看购物车");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        queryWrapper.orderByDesc(ShoppingCart::getCreateTime);
        List<ShoppingCart> list = shoppingCartService.list(queryWrapper);

        return R.success(list);
    }

    /**
     * 清空购物车
     * @return
     */
    @DeleteMapping("/clean")
    public R<String> clean(){
        log.info("清空购物车");
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());
        shoppingCartService.remove(queryWrapper);
        return R.success("清空购物车成功");
    }

    /**
     * 减少菜品
     * @param shoppingCart
     * @return
     */
    @PostMapping("/sub")
    public R<ShoppingCart> sub(@RequestBody ShoppingCart shoppingCart){
        Long setmealId = shoppingCart.getSetmealId();
        Long dishId = shoppingCart.getDishId();
        LambdaQueryWrapper<ShoppingCart> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(ShoppingCart::getUserId, BaseContext.getCurrentId());

        if(setmealId != null){
            queryWrapper.eq(ShoppingCart::getSetmealId, setmealId);
        }else{
            queryWrapper.eq(ShoppingCart::getDishId, dishId);
        }

        ShoppingCart one = shoppingCartService.getOne(queryWrapper);

        Integer number = one.getNumber();
        if(number == 1){
            shoppingCartService.remove(queryWrapper);
        }else{
            one.setNumber(number - 1);
            shoppingCartService.updateById(one);
        }

        return R.success(one);
    }
}
