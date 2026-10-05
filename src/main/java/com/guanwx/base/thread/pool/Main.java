package com.guanwx.base.thread.pool;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/31 星期一 01:57
 * @Description:
 * @Version: 1.0
 */

public class Main {
    public static void main(String[] args) {

        new BizService().doSomeBiz();

        for (int i = 0; i < 4; i++) {
            ThreadPoolUtil.execute(() -> {
                System.out.println("线程池执行：" + Thread.currentThread().getName());
            });
        }

        System.out.println("主线程执行：" + Thread.currentThread().getName());

        // 关闭线程池
        ThreadPoolUtil.shutdown();


    }
}
