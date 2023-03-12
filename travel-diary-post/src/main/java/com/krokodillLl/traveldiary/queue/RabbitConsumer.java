package com.krokodillLl.traveldiary.queue;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RabbitConsumer {

    @RabbitListener(queues = {"${rabbit.queue-name}"})
    public void consume(String message) {
        log.debug("Received message -> {}", message);
    }

}
