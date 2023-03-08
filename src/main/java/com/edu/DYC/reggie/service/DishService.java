package com.edu.DYC.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.DYC.reggie.entity.Dish;
import com.edu.DYC.reggie.vo.DishDto;

/**
 * @author :   Kuroko
 * @date :     2023/2/24
 */
public interface DishService extends IService<Dish> {

    public void saveWithFlavor(DishDto dishDto);

    // 根据 id 查询菜品信息与对应的口味信息
    public DishDto getByIdWithFlavor(Long id);

    // 更新菜品信息
    public void updateWithFlavor(DishDto dishDto);
}
