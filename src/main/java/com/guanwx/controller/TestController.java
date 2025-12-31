package com.guanwx.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/java260101")
public class TestController {

    @GetMapping("/test1")
    public String test1() {
        System.out.println("\n" + "test1");
        return "test1";
    }

}
