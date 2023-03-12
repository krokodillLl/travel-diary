package com.krokodillLl.traveldiary.exchangerate.queue.model.out;

import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class ExchangeRateQueueOut {

    private final String date;
    private final String sourceName;
    private final List<Currency> currencies;

    @Data
    @Builder
    public static class Currency {
        private final String currencyCode;
        private final String currencyName;
        private final Long nominal;
        private final Double value;
    }
}
