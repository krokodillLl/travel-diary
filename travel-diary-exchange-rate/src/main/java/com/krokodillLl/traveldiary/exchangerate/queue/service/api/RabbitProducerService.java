package com.krokodillLl.traveldiary.exchangerate.queue.service.api;

public interface RabbitProducerService {
    void convertAndSend(Object message);
}
