package com.guanwx.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanwx.entity.Orders;
import com.guanwx.mapper.OrderMapper;
import com.guanwx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 05:05
 * @Description:
 * @Version: 1.0
 */

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    public List<Orders> getPageOrderList() {
        IPage<Orders> page = new Page<>(1, 5);
        return orderMapper.selectList(page,null);
    }

    @Override
    public List<Orders> getOrderList() {
        return orderMapper.selectList(null);
    }
}
