package com.guanwx.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanwx.entity.Orders;
import com.guanwx.exception.GlobalException;
import com.guanwx.mapper.OrdersMapper;
import com.guanwx.service.OrdersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 05:05
 * @Description:
 * @Version: 1.0
 */

@Service
public class OrdersServiceImpl implements OrdersService {

    private RedisTemplate<String, List<Orders>> redisTemplate;

    @Autowired
    private OrdersMapper ordersMapper;

    @Override
    public List<Orders> getOrderList() {
        List<Orders> orderList = redisTemplate.opsForValue().get("orderList");
        if (orderList != null) {
            return orderList;
        }
        orderList = ordersMapper.selectList(new QueryWrapper<Orders>().eq("delete_flag", 0));
        redisTemplate.opsForValue().set("orderList", orderList, 1, TimeUnit.DAYS);
        return orderList;
    }

    @Override
    public IPage<Orders> getPageOrderList(Page<Orders> page) {
        if (page == null) {
            page = new Page<>(1, 10);
        }
        return ordersMapper.selectPage(page, new QueryWrapper<Orders>().eq("delete_flag", 0));
    }

    @Override
    public Orders getOrderById(Long id) {
        if (id == null || id <= 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "订单ID不能为空或必须大于0");
        }

        Orders orders = ordersMapper.selectById(id);
        if (orders == null) {
            throw new GlobalException(HttpStatus.NOT_FOUND, "订单不存在");
        }
        return orders;
    }
}
