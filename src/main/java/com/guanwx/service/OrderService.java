package com.guanwx.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
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

    IPage<Orders> getPageOrderList(Page<Orders> page);

    Orders getOrderById(Long id);
}
