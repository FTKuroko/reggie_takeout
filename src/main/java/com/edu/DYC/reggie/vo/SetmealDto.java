package com.edu.DYC.reggie.vo;

import com.edu.DYC.reggie.entity.Setmeal;
import com.edu.DYC.reggie.entity.SetmealDish;
import lombok.Data;

import java.util.List;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */
@Data
public class SetmealDto extends Setmeal {

    // 套餐关联的菜品信息
    private List<SetmealDish> setmealDishes;

    // 分类名称
    private String categoryName;
}
