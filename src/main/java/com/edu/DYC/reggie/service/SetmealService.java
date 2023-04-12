package com.edu.DYC.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.DYC.reggie.entity.Setmeal;
import com.edu.DYC.reggie.vo.SetmealDto;

import java.util.List;

/**
 * @author :   Kuroko
 * @date :     2023/2/24
 */
public interface SetmealService extends IService<Setmeal> {

    // 新增套餐，同时要保持与菜品的关联关系
    public void saveWithDish(SetmealDto setmealDto);

    // 根据 id 查询套餐信息
    public SetmealDto getByIdWithDish(Long id);

    // 修改套餐
    public void updateWithDish(SetmealDto setmealDto);

    public void removeWithDish(List<Long> ids);
}
