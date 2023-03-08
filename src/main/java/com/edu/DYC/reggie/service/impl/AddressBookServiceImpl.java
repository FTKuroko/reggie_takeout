package com.edu.DYC.reggie.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.edu.DYC.reggie.entity.AddressBook;
import com.edu.DYC.reggie.mapper.AddressBookMapper;
import com.edu.DYC.reggie.service.AddressBookService;
import org.springframework.stereotype.Service;

/**
 * @author :   Kuroko
 * @date :     2023/2/25
 */
@Service
public class AddressBookServiceImpl extends ServiceImpl<AddressBookMapper, AddressBook> implements AddressBookService {
}
