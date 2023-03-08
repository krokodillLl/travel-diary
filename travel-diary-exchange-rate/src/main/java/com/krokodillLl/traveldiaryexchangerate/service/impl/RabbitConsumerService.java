package com.krokodillLl.traveldiaryexchangerate.service.impl;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RabbitConsumerService {

    @RabbitListener(queues = {"${rabbit.queue-name}"})
    public void consume(String message) {
        log.debug("Received message -> {}", message);
    }

}
