package com.guanwx.base.jvm;

/**
 * @Author: guanwx
 * @CreateTime: 2026/9/10 星期四 05:28
 * @Description:
 * @Version: 1.0
 */

public class VisibilityDemo {

    private static boolean flag = false;

    public static void main(String[] args) throws InterruptedException {
        long start = System.currentTimeMillis();
        new Thread(() -> {
            while (!flag) {
                // 空循环
//                System.out.println("线程正在等待flag变为true");
            }
            System.out.println("线程感知到flag变为true");
        }).start();
        long end = System.currentTimeMillis();
        Thread.sleep(1000);
        flag = true;
        System.out.println("主线程修改flag=true");
        System.out.println("执行时间: " + (end - start) + " ms");

        String str = "Hello, World!";
        StringBuffer sb1 = new StringBuffer();
        StringBuilder sb2 = new StringBuilder();


    }
}
