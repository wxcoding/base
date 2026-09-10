package com.guanwx.base.thread;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/10 星期四 07:02
 * @Description:
 * @Version: 1.0
 */

public class TestStringBuilder {
    public static void main(String[] args) throws Exception {

        StringBuilder stringBuilder = new StringBuilder();
        Runnable runnable = () -> {
            for (int i = 0; i < 1000; i++) {
                stringBuilder.append("a");
            }
        };
        Thread thread1 = new Thread(runnable);
        Thread thread2 = new Thread(runnable);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println("StringBuilder length: " + stringBuilder.length());


    }

}
