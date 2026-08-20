package com.guanwx.mq;

import com.guanwx.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/16 星期日 06:55
 * @Description:
 * @Version: 1.0
 */

@Component
public class MessageConsumer {

    @RabbitListener(queues = RabbitMQConfig.QUEUE_NAME)
    public void receive(String message) {
        System.out.println("【消费者】收到消息：" + message);
    }

}
