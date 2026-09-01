package com.guanwx.base.design.proxy.jdk;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/2 星期三 00:14
 * @Description: JDK 动态代理处理器，负责在调用真实对象前后做增强
 * @Version: 1.0
 */
public class ProxyRoom implements InvocationHandler {

    private final Object target;

    public ProxyRoom(Object target) {
        this.target = target;
    }

    public Object bind() {
        return Proxy.newProxyInstance(
                target.getClass().getClassLoader(),
                target.getClass().getInterfaces(),
                this
        );
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("before invoke: " + method.getName());
        Object result = method.invoke(target, args);
        System.out.println("after invoke: " + method.getName());
        return result;
    }
}
