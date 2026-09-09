package com.guanwx.controller;

import com.guanwx.common.Result;
import com.guanwx.entity.User;
import com.guanwx.mq.MessageProducer;
import com.guanwx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/7 星期五 03:23
 * @Description:
 * @Version: 1.0
 */

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 根据名称和年龄查询用户
    @GetMapping("/getUser")
    public Result<List<User>> getUser(String name, Integer age) {
        return Result.success(userService.selectListByNameAndAge(name, age));
    }

    // 查询所有用户
    @GetMapping("/getUserList")
    public Result<List<User>> getUserList() {
        return Result.success(userService.selectList());
    }

    // 添加用户
    @GetMapping("/addUser")
    public Result<Boolean> addUser(@RequestParam(required = false) User user) {
        if (user ==null) {
            user = new User();
            user.setAge(18);
            user.setName("小明");
            user.setEmail("xiaoming@example.com");
        }
        return Result.success(userService.addUser(user));
    }

    // 模糊查询
    @GetMapping("/getUserByName")
    public Result<List<User>> getUserByName(@RequestParam String name) {
        return Result.success(userService.selectListByName(name));
    }

}
