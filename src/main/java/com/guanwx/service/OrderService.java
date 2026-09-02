package com.guanwx.service;

import com.guanwx.entity.Orders;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 05:04
 * @Description:
 * @Version: 1.0
 */

public interface OrderService {

    List<Orders> getOrderList();

    List<Orders> getPageOrderList();


}
