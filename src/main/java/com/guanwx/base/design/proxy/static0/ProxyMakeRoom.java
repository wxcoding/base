package com.guanwx.base.design.proxy.static0;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 00:11
 * @Description:
 * @Version: 1.0
 */

public class ProxyMakeRoom extends MakeRoom{

    @Override
    public void rent() {
        System.out.println("中介带你看房子");
        super.rent();
        System.out.println("中介收取中介费");
    }


}
