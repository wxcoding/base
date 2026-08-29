package com.guanwx.base.design.single;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/28 星期五 22:23
 * @Description:
 * @Version: 1.0
 */

public class Singleton2 {

    private static volatile Singleton2 singleton2;

    public Singleton2() {};

    public Singleton2 getSingleton2() {
        if (singleton2 == null) {
            synchronized (Singleton2.class) {
                if (singleton2 == null) {
                    singleton2 = new Singleton2();
                }
            }
        }
        return singleton2;
    }
}
