package com.guanwx.controller;

import com.guanwx.common.Result;
import com.guanwx.entity.Users;
import com.guanwx.mapper.UsersMapper;
import com.guanwx.service.UsersService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
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
@Tag(name = "用户管理", description = "用户查询相关接口")
public class UsersController {

    @Autowired
    private UsersMapper usersMapper;

    @Autowired
    private UsersService usersService;

    @Operation(summary = "根据用户名查询用户")
    @GetMapping("/getUsersByUsername/{username}")
    public Result<List<Users>> getUsersByUsername (@PathVariable String username) {
        return Result.success(usersService.getUsersByUsername(username));
    }

    @Operation(summary = "用户登录接口", description = "根据用户ID和用户密码校验登录信息")
    @GetMapping("/login")
    public Result<String> login (@RequestParam String userId, String password) {
        return Result.success(usersService.login(userId, password));
    }

}
