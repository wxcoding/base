package com.guanwx.base.design.proxy.static0;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 00:10
 * @Description:
 * @Version: 1.0
 */

public class MakeRoom implements Room{

    @Override
    public void rent() {
        System.out.println("房东出租房子");
    }
}
