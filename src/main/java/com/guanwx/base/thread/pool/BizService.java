package com.guanwx.base.thread.pool;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/31 星期一 01:56
 * @Description:
 * @Version: 1.0
 */

public class BizService {

    public void doSomeBiz() {
        // 异步执行，不会阻塞当前主线程
        ThreadPoolUtil.execute(() -> {
            // 耗时任务：发送短信、日志上报、文件导出、调用第三方接口
            System.out.println("异步任务执行:" + Thread.currentThread().getName());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        // 主线程继续往下走，不受上面任务阻塞
        System.out.println("主线程继续执行业务");
    }

    public void testFuture() throws ExecutionException, InterruptedException {
        Future<String> future = ThreadPoolUtil.submit(() -> {
            Thread.sleep(1000);
            return "任务结果";
        });
        // get() 会阻塞，直到拿到结果
        String res = future.get();
        System.out.println(res);
    }


}
