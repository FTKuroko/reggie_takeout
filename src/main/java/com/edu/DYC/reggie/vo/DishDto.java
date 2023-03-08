package com.edu.DYC.reggie.vo;

import com.edu.DYC.reggie.entity.Dish;
import com.edu.DYC.reggie.entity.DishFlavor;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author :   Kuroko
 * @date :     2023/2/24
 */

/**
 * DTO，全称为Data Transfer object，即数据传输对象，一般用于展示层与服务层之间的数据传输。
 * 封装页面提交的数据
 */
@Data
public class DishDto extends Dish {
    private List<DishFlavor> flavors = new ArrayList<>();

    private String categoryName;

    private Integer copies;
}
