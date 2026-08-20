package com.guanwx.base.thread;

/**
 * @Author: guanwx
 * @CreateTime: 2026/4/7 星期二 08:54
 * @Description:
 * @Version: 1.0
 */

public class TestThread extends Thread{
    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println("aaa");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("bbb");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("ccc");
        });

        thread1.start();
        thread2.start();
        thread3.start();

    }
}
