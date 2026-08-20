package com.guanwx.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: guanwx
 * @CreateTime: 2026/8/16 星期日 06:52
 * @Description:
 * @Version: 1.0
 */

@Configuration
public class RabbitMQConfig {

    public static final String QUEUE_NAME = "demo.queue";
    public static final String EXCHANGE_NAME = "demo.exchange";
    public static final String ROUTING_KEY = "demo.routingKey";

    @Bean
    public Queue queue() {
        return new Queue(QUEUE_NAME, true);
    }

    @Bean
    public DirectExchange exchange() {
        return new DirectExchange(EXCHANGE_NAME);
    }

    @Bean
    public Binding binding(Queue queue, DirectExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with(ROUTING_KEY);
    }

}
