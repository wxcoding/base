package com.guanwx.service.cloud;

import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.util.Collections;
import java.util.Objects;
import java.util.UUID;
import java.util.function.BooleanSupplier;

@Service
public class RedisLock {
    
    // 每个订单使用独立的锁，避免不同订单之间互相阻塞。
    private static final String ORDER_GRAB_LOCK_PREFIX = "lock:order:grab:";
    // 锁的最长有效期，业务执行时间超过该时长时锁会自动过期。
    private static final Duration LOCK_LEASE = Duration.ofSeconds(30);

    // 比较锁值和删除锁在同一个 Redis 脚本中执行，避免先检查后删除之间发生并发问题。
    private static final DefaultRedisScript<Long> UNLOCK_SCRIPT = new DefaultRedisScript<>(
            "if redis.call('get', KEYS[1]) == ARGV[1] then " +
                    "return redis.call('del', KEYS[1]) " +
                    "else return 0 end",
            Long.class
    );

    private final StringRedisTemplate stringRedisTemplate;

    public RedisLock(StringRedisTemplate stringRedisTemplate) {
        this.stringRedisTemplate = stringRedisTemplate;
    }

    /**
     * 使用 Redis 锁保护抢单操作。
     *
     * <p>业务伪代码：</p>
     * <pre>
     * 校验订单和用户
     * 调用 orderGrabbing(订单ID, () -&gt; {
     *     开启数据库事务
     *     执行条件更新：仅当订单状态为“待抢”时，更新为“已抢”并记录抢单用户
     *     根据受影响行数判断抢单成功或失败
     *     提交事务
     *     返回是否更新成功
     * })
     * 根据返回值提示“抢单成功”或“抢单失败/正在处理中”
     * </pre>
     *
     * <p>Redis 锁用于减少并发请求竞争，数据库条件更新才是防止重复抢单的最终保障。
     * 锁最长有效期为 30 秒；如果业务可能超过该时间，应调整租期或设计续期机制。</p>
     *
     * @return 获取到锁且抢单成功时返回 true；未获取到锁或抢单失败时返回 false
     */
    public boolean orderGrabbing(String orderId, BooleanSupplier claimOrder) {
        // 无效参数直接报错，避免生成无意义的锁键或执行空业务逻辑。
        if (!StringUtils.hasText(orderId)) {
            throw new IllegalArgumentException("订单ID不能为空");
        }
        Objects.requireNonNull(claimOrder, "抢单操作不能为空");

        // Redis 锁键按订单隔离；随机值用于标识本次请求，确保只能释放自己持有的锁。
        String lockKey = ORDER_GRAB_LOCK_PREFIX + orderId;
        String lockValue = UUID.randomUUID().toString();

        // 等价于 SET lockKey lockValue NX EX 30：仅当键不存在时加锁，并同时设置过期时间。
        Boolean locked = stringRedisTemplate.opsForValue()
                .setIfAbsent(lockKey, lockValue, LOCK_LEASE);
        if (!Boolean.TRUE.equals(locked)) {
            return false;
        }

        try {
            // 只有获取锁成功的请求才会执行抢单业务。
            return claimOrder.getAsBoolean();
        } finally {
            // 只有 Redis 中的锁值仍属于当前请求时才删除，避免误删后来请求获得的锁。
            stringRedisTemplate.execute(UNLOCK_SCRIPT, Collections.singletonList(lockKey), lockValue);
        }
    }
}
