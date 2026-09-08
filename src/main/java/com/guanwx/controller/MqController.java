package com.guanwx.controller;

import com.guanwx.common.Result;
import com.guanwx.mq.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/8 星期二 03:09
 * @Description:
 * @Version: 1.0
 */

@RestController
@RequestMapping("/mq")
public class MqController {

    @Autowired
    private MessageProducer messageProducer;

    // 发送消息
    @GetMapping("/sendMsg")
    public Result<String> sendMsg(@RequestParam String msg) {
        messageProducer.send(msg);
        return Result.success("消息已发送");
    }

}
