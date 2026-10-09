package com.guanwx.base.design.single;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/7 星期五 23:38
 * @Description:
 * @Version: 1.0
 */

public class Singleton1 {

    private static volatile Singleton1 singleton1;

    private Singleton1() {

    }

    public static Singleton1 getSingleton() {
        if(singleton1 == null) {
            synchronized (Singleton1.class) {
                if (singleton1 == null) {
                    singleton1 = new Singleton1();
                }
            }
        }
        return singleton1;
    }
}
