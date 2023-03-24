package com.krokodillLl.traveldiary.queue;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krokodillLl.traveldiaryspringbootstarter.model.queue.ExchangeRateQueue;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitConsumer {
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Cacheable(value = "rabbitExchangeRate")
    @RabbitListener(queues = {"${rabbit.queue-name}"})
    public ExchangeRateQueue consume(String message) {
        log.debug("Received message -> {}", message);
        return objectMapper.readValue(message, ExchangeRateQueue.class);
    }

}
