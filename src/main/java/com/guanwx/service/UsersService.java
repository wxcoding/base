package com.guanwx.service;

import com.guanwx.entity.Users;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/25 星期二 01:51
 * @Description:
 * @Version: 1.0
 */

public interface UsersService {

    List<Users> getUsersByUsername(String username);

    String login(String userId, String password);


}
