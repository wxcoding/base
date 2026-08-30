package com.guanwx.base.thread.pool;

import java.util.concurrent.*;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/31 星期一 01:51
 * @Description:
 * @Version: 1.0
 */

public class ThreadPoolUtil {

    /**
     * 私有静态实例：全局单例线程池
     * <p>
     * 设计说明：
     * - corePoolSize = 可用CPU数量（保证在低负载时有足够线程执行任务）
     * - maximumPoolSize = cpu * 2（在突发负载时允许扩容，但有限制以控制并发）
     * - keepAliveTime = 60s（超出 core 后空闲线程保留时间，超过则回收）
     * - workQueue = ArrayBlockingQueue(200)（有界队列，防止无限制内存增长）
     * - threadFactory = CustomThreadFactory（便于线程命名、调试与统计）
     * - rejectionPolicy = CallerRunsPolicy（当队列与线程都满时，让调用者线程运行任务，作为降级策略）
     */
    private static final ThreadPoolExecutor BUSINESS_POOL;

    /**
     * 静态初始化块：在类加载时创建并配置线程池
     * 这里把初始化放在静态块中，确保线程池为类级单例且只初始化一次。
     * 若需更复杂的配置（如通过外部配置文件），可改造为懒加载或工厂方式。
     */
    static {
        int cpu = Runtime.getRuntime().availableProcessors();
        BUSINESS_POOL = new ThreadPoolExecutor(
                // corePoolSize：基础并发数，保持该数量的线程常驻
                cpu,
                // maximumPoolSize：最大并发数，允许突发时扩容到 cpu * 2
                cpu * 2,
                // keepAliveTime：超过 corePoolSize 后，空闲线程在回收前保持的时间
                60L,
                TimeUnit.SECONDS,
                // 队列大小为 200 的有界队列，避免无限制排队导致 OOM
                new ArrayBlockingQueue<>(200),
                // 自定义线程工厂，方便线程命名和设置 daemon 状态
                new CustomThreadFactory("business-pool-"),
                // 拒绝策略：CallerRunsPolicy 将任务回退到提交者线程，作为温和的降级手段
                new ThreadPoolExecutor.CallerRunsPolicy()
        );
    }

    /**
     * 提交无返回值任务（Runnable）。
     * 使用 execute 更适合火并发提交且不关心结果的场景。
     * 任何由线程池抛出的 RejectedExecutionException 取决于拒绝策略；
     * 这里使用 CallerRunsPolicy，会在饱和时由调用线程执行任务，而不是抛出。
     */
    public static void execute(Runnable task) {
        BUSINESS_POOL.execute(task);
    }

    /**
     * 提交有返回值任务（Callable），返回 Future 用于后续获取结果或取消。
     * 推荐在需要结果或异常捕获时使用 submit；注意 future.get() 可能阻塞。
     */
    public static <T> Future<T> submit(Callable<T> task) {
        return BUSINESS_POOL.submit(task);
    }

    /**
     * 暴露线程池实例（谨慎使用）：
     * - 提供扩展操作，如查询当前活跃线程数、调整策略或添加监控。
     * - 不建议外部随意 shutdown/修改配置，除非在受控场景（如应用关闭钩子）。
     */
    public static ThreadPoolExecutor getPool() {
        return BUSINESS_POOL;
    }

    /**
     * 关闭线程池：建议在应用停止/销毁时调用（例如 JVM 钩子）。
     * 调用 shutdown() 后线程池不再接受新任务，已提交任务会继续执行。
     * 若需要立即中断正在执行的任务，可改用 shutdownNow() 并结合额外的清理逻辑。
     */
    public static void shutdown() {
        BUSINESS_POOL.shutdown();
    }

    //自定义线程工厂
    static class CustomThreadFactory implements ThreadFactory {
        private final String prefix;
        private int num = 0;
        public CustomThreadFactory(String prefix) {
            this.prefix = prefix;
        }
        @Override
        public Thread newThread(Runnable r) {
            Thread thread = new Thread(r, prefix + (num++));
            thread.setDaemon(false);
            return thread;
        }
    }



}
