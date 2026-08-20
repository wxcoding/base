package com.guanwx.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.guanwx.service.cloud.CloudLock;
import com.guanwx.entity.User;
import com.guanwx.mapper.UserMapper;
import com.guanwx.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public String test1() {
        System.out.println("\n" + "test1");
        return "test1";
    }

    @GetMapping("/getUser")
    public List<User> getUser() {
        List<User> selectList = userMapper.selectList(new LambdaQueryWrapper<User>());
        selectList.forEach(System.out::println);
        return selectList;

    }

    @GetMapping("/getUserById/{id}")
    public User getUserById(@PathVariable("id") Integer id) {
        return userMapper.selectById(id);
    }

    @GetMapping("/getUserByName/{name}")
    public List<User> getUserByName(@PathVariable("name") String name) {
        return userMapper.selectListByName(name);
    }

    @GetMapping("/updateUserById1")
    public int updateUserById(@RequestParam("id") Integer id,
                              @RequestParam("name") String name,
                              @RequestParam("age") Integer age) {
        int updateRows = userMapper.updateById1(name, age, id);
        System.out.println("updateRows: " + updateRows);
        return updateRows;
    }

    @GetMapping("/lock/{orderId}")
    public boolean lockOrder(@PathVariable("orderId") String orderId) {
        return cloudLock.orderGrabbing(orderId);
    }

}
