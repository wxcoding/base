package com.guanwx.service.impl;

import com.guanwx.entity.Users;
import com.guanwx.mapper.UsersMapper;
import com.guanwx.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/25 星期二 01:55
 * @Description:
 * @Version: 1.0
 */

@Service
public class UsersServiceImpl implements UsersService {

    @Autowired
    private UsersMapper usersMapper;

    @Override
    public List<Users> getUsersByUsername(String username) {
        return usersMapper.getUsersByUsername(username);
    }
}
