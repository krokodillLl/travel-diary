package com.krokodillLl.traveldiary.exchangerate.queue.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.krokodillLl.traveldiary.exchangerate.queue.service.api.RabbitProducerService;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitProducerServiceImpl implements RabbitProducerService {
    private final RabbitTemplate rabbitTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @SneakyThrows
    @Override
    public void convertAndSend(Object message) {
        rabbitTemplate.convertAndSend(
                objectMapper.writeValueAsString(message)
        );
    }
}
