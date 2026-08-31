package com.guanwx.controller;

import com.guanwx.entity.Users;
import com.guanwx.mapper.UsersMapper;
import com.guanwx.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.swing.*;
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
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @GetMapping("/getUsersByUsername/{username}")
    public List<Users> getUsersByUsername (@PathVariable String username) {
        return usersService.getUsersByUsername(username);
    }

    @PostMapping("/login")
    public String login (@RequestBody Users user) {
        return usersService.login(user.getId(), user.getPasswordHash());
    }

}
