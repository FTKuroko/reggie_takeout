package com.edu.DYC.reggie.vo;

import com.edu.DYC.reggie.entity.OrderDetail;
import com.edu.DYC.reggie.entity.Orders;
import lombok.Data;

import java.util.List;

/**
 * @author :   Kuroko
 * @date :     2023/2/26
 */
@Data
public class OrdersDto extends Orders {
    private String userName;

    private String phone;

    private String address;

    private String consignee;

    private List<OrderDetail> orderDetails;

}
