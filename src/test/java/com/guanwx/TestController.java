package com.guanwx;

import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.guanwx.entity.User;
import com.guanwx.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class TestController {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void getUser() {
        List<User> list = userMapper.selectList(null);
        System.out.println(list);
    }

}
