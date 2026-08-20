package com.guanwx.service.impl;

import com.guanwx.entity.User;
import com.guanwx.mapper.UserMapper;
import com.guanwx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/7 星期五 03:18
 * @Description:
 * @Version: 1.0
 */

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private RedisTemplate<String, Object> redisTemplate;

    @Autowired
    private UserMapper userMapper;

    // 根据名称和年龄查询用户
    public List<User> selectListByNameAndAge(String name, Integer age) {
        return userMapper.selectListByNameAndAge(name, age);
    }

    // 查询所有用户
    public List<User> selectList() {
        long start = System.currentTimeMillis();
        List<User> usersAll = (List<User>) redisTemplate.opsForValue().get("usersAll");
        if (usersAll != null) {
            System.out.println("从缓存中获取数据，耗时：" + (System.currentTimeMillis() - start) + "ms");
            return usersAll;
        }
        usersAll = userMapper.selectList(null);
        redisTemplate.opsForValue().set("usersAll", usersAll, 10, TimeUnit.SECONDS);
        System.out.println("从数据库中获取数据，耗时：" + (System.currentTimeMillis() - start) + "ms");
        return usersAll;
    }


}
