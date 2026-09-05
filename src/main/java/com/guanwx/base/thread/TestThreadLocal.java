package com.guanwx.base.thread;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/30 星期日 22:17
 * @Description:
 * @Version: 1.0
 */

public class TestThreadLocal {

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) {

        System.out.println(threadLocal.get());

        thread1();

        thread2();

        thread3();

    }

    public static void thread1 () {
        new Thread(() -> {
            threadLocal.set("thread1");
            System.out.println(threadLocal.get());
            threadLocal.remove();
        }).start();
    }

    public static void thread2 () {
        new Thread(() -> {
//            threadLocal.set("thread2");
            System.out.println(threadLocal.get());
        }).start();
    }

    public static void thread3 () {
        new Thread (() -> {
            threadLocal.set("thread3");
            System.out.println(threadLocal.get());
            threadLocal.remove();
        }).start();
    }


}
