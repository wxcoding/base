package com.guanwx.service.cloud;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/10 星期一 17:31
 * @Description:
 * @Version: 1.0
 */

@Service
public class CloudLock {

    @Autowired
    private RedissonClient redissonClient;

    public boolean orderGrabbing (String orderId) {
        //  判断订单ID是否存在
        if (!StringUtils.hasText(orderId)) {
            throw new RuntimeException("订单ID不能为空");
        }

        // 创建锁
        RLock lock = redissonClient.getLock(orderId);

        // 获取锁
        try {
            boolean isLock = lock.tryLock();
            if (!isLock) {
                throw new RuntimeException("获取锁失败！抢单失败！");
            }
            // 处理订单，更新订单状态
            String orderStatus = "已抢单";
            System.out.println("订单ID：" + orderId + "，订单状态：" + orderStatus);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }finally {
            if (lock.isHeldByCurrentThread()) {
                // 释放锁
                lock.unlock();
            }
        }
        return true;
    }



}
