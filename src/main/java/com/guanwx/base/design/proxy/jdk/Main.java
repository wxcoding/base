package com.guanwx.base.design.proxy.jdk;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 00:29
 * @Description:
 * @Version: 1.0
 */
public class Main {
    public static void main(String[] args) {

        Room proxy = (Room) new ProxyRoom(new MakeRoom()).bind();
        proxy.rent();

    }
}
