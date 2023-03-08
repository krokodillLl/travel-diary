package com.krokodillLl.traveldiaryexchangerate.service.impl;

import com.krokodillLl.traveldiaryexchangerate.service.api.RabbitService;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RabbitServiceImpl implements RabbitService {
    @Value("${rabbit.exchange-name}")
    private String exchangeName;
    @Value("${rabbit.routing-key}")
    private String routingKey;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void sendMessage(String message) {
      log.debug("*************************************{}*********************************", message);

      rabbitTemplate.convertAndSend(exchangeName, routingKey, message);
    }
}
