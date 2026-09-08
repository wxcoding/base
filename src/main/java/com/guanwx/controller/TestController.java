package com.guanwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guanwx.common.Result;
import com.guanwx.service.cloud.CloudLock;
import com.guanwx.entity.User;
import com.guanwx.mapper.UserMapper;
import com.guanwx.service.impl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private CloudLock cloudLock;

    @Autowired
    UserMapper userMapper;

    @Autowired
    private UserServiceImpl UserService;

    @GetMapping("/test1")
    public Result<String> test1() {
        log.info("test1");
        return Result.success("test1");
    }

    @GetMapping("/getUser")
    public Result<List<User>> getUser() {
        List<User> selectList = userMapper.selectList(new LambdaQueryWrapper<User>());
        return Result.success(selectList);
    }

    @GetMapping("/getUserById/{id}")
    public Result<User> getUserById(@PathVariable Integer id) {
        return Result.success(userMapper.selectById(id));
    }

    @GetMapping("/getUserByName/{name}")
    public Result<List<User>> getUserByName(@PathVariable String name) {
        return Result.success(userMapper.selectListByName(name));
    }

    @GetMapping("/updateUserById1")
    public Result<Integer> updateUserById(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("age") Integer age) {
        int updateRows = userMapper.updateById1(name, age, id);
        log.info("updateRows: {}", updateRows);
        return Result.success(updateRows);
    }

    @GetMapping("/lock/{orderId}")
    public Result<Boolean> lockOrder(@PathVariable String orderId) {
        return Result.success(cloudLock.orderGrabbing(orderId));
    }

}
