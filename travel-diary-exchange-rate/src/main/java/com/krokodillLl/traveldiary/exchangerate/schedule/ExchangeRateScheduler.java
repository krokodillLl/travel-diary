package com.krokodillLl.traveldiary.exchangerate.schedule;

import com.krokodillLl.traveldiary.exchangerate.external.ExchangeRateRequester;
import com.krokodillLl.traveldiary.exchangerate.queue.service.api.RabbitProducerService;
import com.krokodillLl.traveldiary.exchangerate.util.ExternalModelToQueueModelConverter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
@Slf4j
public class ExchangeRateScheduler {
    private final ExchangeRateRequester exchangeRateRequester;
    private final ExternalModelToQueueModelConverter converter;
    private final RabbitProducerService rabbitService;

    @Scheduled(cron = "${scheduler.cron-expression}")
    private void updateExchangeRate() {
        log.info("updateExchangeRate() is starting");
        rabbitService.convertAndSend(
                converter.convert(
                        exchangeRateRequester.getCurrentExchangeRates()
                )
        );
        log.info("updateExchangeRate() is finishing");
    }
}
