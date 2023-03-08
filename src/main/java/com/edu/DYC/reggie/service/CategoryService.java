package com.edu.DYC.reggie.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.edu.DYC.reggie.entity.Category;

/**
 * @author :   Kuroko
 * @date :     2023/2/24
 */
public interface CategoryService extends IService<Category> {
    public void remove(Long id);
}
