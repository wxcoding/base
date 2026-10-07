package com.guanwx.base.thread.pool;

import org.apache.poi.ss.formula.functions.T;

import java.util.concurrent.*;

/**
 * @Author: guanwx
 * @CreateTime: 2026/10/7 星期三 00:41
 * @Description:
 * @Version: 1.0
 */

public class ThreadPoolUtils2 {

    // 线程池执行器
    private static final ThreadPoolExecutor THREAD_POOL_EXECUTOR;

    // 自定义线程池参数
    static {
        THREAD_POOL_EXECUTOR = new ThreadPoolExecutor(
                2,
                4,
                60,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(100),
                new CustomThreadFactory("CustomThread"),
                new ThreadPoolExecutor.AbortPolicy()
        );
    }

    // 提交无返回值任务（Runnable）。
    public static void execute(Runnable task) {
        THREAD_POOL_EXECUTOR.execute(task);
    }

    // 提交有返回值任务（Callable）。
    public static Future<T> submit(Callable<T> task) {
        return THREAD_POOL_EXECUTOR.submit(task);
    }

    // 关闭线程池
    public static void shutdown() {
        THREAD_POOL_EXECUTOR.shutdown();
    }

    // 自定义线程工厂
    private static class CustomThreadFactory implements ThreadFactory {
        private final String namePrefix;
        private int count = 0;

        public CustomThreadFactory(String namePrefix) {
            this.namePrefix = namePrefix;
        }

        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, namePrefix + "-" + count++);
        }

    }

}