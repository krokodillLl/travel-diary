package com.krokodillLl.traveldiary.exchangerate.configuration;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class RabbitConfig {
    @Value("${rabbit.queue-name}")
    private String queueName;
    @Value("${rabbit.exchange-name}")
    private String exchangeName;
    @Value("${rabbit.routing-key}")
    private String routingKey;

    private final RabbitTemplate rabbitTemplate;

    @PostConstruct
    private void init() {
            rabbitTemplate.setExchange(exchangeName);
            rabbitTemplate.setRoutingKey(routingKey);
    }

    @Bean
    public Queue getQueue() {
        return new Queue(queueName);
    }

    @Bean
    public TopicExchange getTopicExchange() {
        return new TopicExchange(exchangeName);
    }

    @Bean
    public Binding getBinding() {
        return BindingBuilder
                .bind(getQueue())
                .to(getTopicExchange())
                .with(routingKey);
    }

}
