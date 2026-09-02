package com.guanwx.controller;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.guanwx.common.Result;
import com.guanwx.entity.Orders;
import com.guanwx.exception.GlobalException;
import com.guanwx.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
    public Result<IPage<Orders>> getPageOrderList(
            @RequestParam(defaultValue = "1") Long current,
            @RequestParam(defaultValue = "10") Long size) {
        Page<Orders> page = new Page<>(current, size);
        return Result.success(orderService.getPageOrderList(page));
    }

    @GetMapping("/{id}")
    public Result<Orders> getOrderById(@PathVariable Long id) {
        if (id == null || id <= 0) {
            throw new GlobalException(HttpStatus.BAD_REQUEST, "订单ID必须大于0");
        }
        return Result.success(orderService.getOrderById(id));
    }
}
