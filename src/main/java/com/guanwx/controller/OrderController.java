package com.guanwx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.guanwx.common.Result;
import com.guanwx.entity.Orders;
import com.guanwx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 05:05
 * @Description:
 * @Version: 1.0
 */

@RestController
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    @GetMapping("/orderList")
    public Result<List<Orders>> getOrderList() {
        return Result.success(orderService.getOrderList());
    }

    @GetMapping("/pageOrderList")
    public Result<List<Orders>> getPageOrderList() {
        return Result.success(orderService.getPageOrderList());
    }

}
