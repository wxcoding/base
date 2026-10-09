package com.guanwx.base.design.single;

/**
 * @Author: guanwx
 * @CreateTime: 2026/10/8 星期四 09:15
 * @Description:
 * @Version: 1.0
 */

public class Singleton3 {

    private static volatile Singleton3 singleton3;

    private Singleton3() {

    }

    public static Singleton3 getInstance3() {
        if (singleton3 == null) {
            synchronized (Singleton3.class) {
                if (singleton3 == null) {
                    singleton3 = new Singleton3();
                }
            }
        }
        return singleton3;
    }
}
