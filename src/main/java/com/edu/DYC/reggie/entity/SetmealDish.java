package com.edu.DYC.reggie.entity;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */
@Data
public class SetmealDish implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;

    // 套餐 id
    private Long setmealId;

    // 菜品 id
    private Long dishId;

    // 菜品名称
    private String name;

    // 菜品原价
    private BigDecimal price;

    // 份数
    private Integer copies;

    // 排序
    private Integer sort;

    @TableField(fill = FieldFill.INSERT)
    private LocalDateTime createTime;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private LocalDateTime updateTime;

    @TableField(fill = FieldFill.INSERT)
    private Long createUser;

    @TableField(fill = FieldFill.INSERT_UPDATE)
    private Long updateUser;

    // 是否删除
    private Integer isDeleted;
}
