package com.edu.DYC.reggie.entity;

import lombok.Data;

import java.io.Serializable;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */

/**
 * 用户信息
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;
    private Long id;

    // 姓名
    private String name;

    // 手机号
    private String phone;

    // 性别
    private String sex;

    // 身份证号
    private String idNumber;

    // 头像
    private String avatar;

    // 状态 0：禁用， 1：正常
    private Integer status;
}
