package com.guanwx.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.guanwx.entity.User;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/8 星期六 06:13
 * @Description:
 * @Version: 1.0
 */

public interface UserService extends IService<User> {

    // 根据名称和年龄查询用户
    List<User> selectListByNameAndAge(String name, Integer age);

    // 查询所有用户
    List<User> selectList();

    boolean addUser(User user);

    List<User> selectListByName(String name);

}
