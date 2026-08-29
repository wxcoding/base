package com.guanwx.base.design;

/**
 * @Author: guanwx
 * @CreateTime: 2026/5/25 星期一 03:23
 * @Description:
 * @Version: 1.0
 */

public class Singleton {

    private static volatile Singleton singleton;

    public Singleton(){

    }

    public Singleton getInstance() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }



}
