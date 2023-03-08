package com.edu.DYC.reggie.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.DYC.reggie.common.CustomException;
import com.edu.DYC.reggie.entity.Category;
import com.edu.DYC.reggie.entity.Dish;
import com.edu.DYC.reggie.entity.Setmeal;
import com.edu.DYC.reggie.mapper.CategoryMapper;
import com.edu.DYC.reggie.service.CategoryService;
import com.edu.DYC.reggie.service.DishService;
import com.edu.DYC.reggie.service.SetmealService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author :   Kuroko
 * @date :     2023/2/24
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
    @Autowired
    private DishService dishService;

    @Autowired
    private SetmealService setmealService;


    @Override
    public void remove(Long id) {
        LambdaQueryWrapper<Dish> dishLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 添加查询条件
        dishLambdaQueryWrapper.eq(Dish::getCategoryId, id);
        int count1 = dishService.count(dishLambdaQueryWrapper);

        // 查询当前分类是否关联菜品，如果已经关联，抛出业务异常
        if(count1 > 0){
            throw new CustomException("已关联菜品，不能删除");
        }

        // 查询当前分类是否关联套餐，如果已经关联，抛出业务异常
        LambdaQueryWrapper<Setmeal> setmealLambdaQueryWrapper = new LambdaQueryWrapper<>();
        setmealLambdaQueryWrapper.eq(Setmeal::getCategoryId, id);
        int count2 = setmealService.count(setmealLambdaQueryWrapper);

        if(count2 > 0){
            throw new CustomException("已经关联套餐，不能删除");
        }

        // 正常删除分类
        super.removeById(id);
    }
}
