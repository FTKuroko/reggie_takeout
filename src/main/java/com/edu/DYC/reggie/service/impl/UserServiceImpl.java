package com.edu.DYC.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.DYC.reggie.entity.User;
import com.edu.DYC.reggie.mapper.UserMapper;
import com.edu.DYC.reggie.service.UserService;
import org.springframework.stereotype.Service;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {
}
