package com.guanwx.controller;

import com.guanwx.entity.Users;
import com.guanwx.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/25 星期二 01:57
 * @Description:
 * @Version: 1.0
 */

@RestController
@RequestMapping("/users")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUsersByUsername/{username}")
    public List<Users> getUsersByUsername (@PathVariable("username") String username) {
        return usersService.getUsersByUsername(username);
    }

}
