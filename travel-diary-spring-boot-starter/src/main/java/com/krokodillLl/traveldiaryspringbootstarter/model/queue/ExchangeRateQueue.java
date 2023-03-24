package com.krokodillLl.traveldiaryspringbootstarter.model.queue;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ExchangeRateQueue {

    private String date;
    private String sourceName;
    private List<Currency> currencies;

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    @Builder
    public static class Currency {
        private String currencyCode;
        private String currencyName;
        private Long nominal;
        private Double value;
    }
}
