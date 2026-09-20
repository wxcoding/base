package com.guanwx.base.list;

import java.util.Vector;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/21 星期一 04:27
 * @Description:
 * @Version: 1.0
 */

public class TestVector {

    public static void main(String[] args) {

        Vector<String> vector = new Vector<>();

        // 创建两个线程，演示 Vector 的线程安全性
        Thread thread1 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                vector.add("Thread1-" + i);
                System.out.println("Thread1 added: " + "Thread1-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            for (int i = 0; i < 5; i++) {
                vector.add("Thread2-" + i);
                System.out.println("Thread2 added: " + "Thread2-" + i);
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        thread1.start();
        thread2.start();

        try {
            thread1.join();
            thread2.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        System.out.println("Final Vector: " + vector);

    }



}
