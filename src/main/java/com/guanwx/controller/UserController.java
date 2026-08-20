package com.guanwx.controller;

import com.guanwx.entity.User;
import com.guanwx.mq.MessageProducer;
import com.guanwx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    private MessageProducer messageProducer;

    @Autowired
    private UserService userService;

    // 根据名称和年龄查询用户
    @RequestMapping("/getUser")
    public List<User> getUser(String name, Integer age) {
        return userService.selectListByNameAndAge(name, age);
    }

    // 查询所有用户
    @RequestMapping("/getUserList")
    public List<User> getUserList() {
        return userService.selectList();
    }

    // 发送消息
    @RequestMapping("/sendMsg")
    public String sendMsg(@RequestParam String msg) {
        messageProducer.send(msg);
        return "消息已发送";
    }
}
