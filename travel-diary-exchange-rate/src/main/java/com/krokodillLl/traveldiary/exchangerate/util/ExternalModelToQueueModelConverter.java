package com.krokodillLl.traveldiary.exchangerate.util;

import com.krokodillLl.traveldiary.exchangerate.external.model.generated.ValCurs;
import com.krokodillLl.traveldiary.exchangerate.queue.model.out.ExchangeRateQueueOut;
import lombok.SneakyThrows;
import org.springframework.stereotype.Component;

import java.text.NumberFormat;
import java.text.ParseException;
import java.util.List;
import java.util.Locale;

@Component
public class ExternalModelToQueueModelConverter {
    public ExchangeRateQueueOut convert(ValCurs externalCurrentExchangeRates) {
        return ExchangeRateQueueOut.builder()
                .date(externalCurrentExchangeRates.getDate())
                .sourceName(externalCurrentExchangeRates.getName())
                .currencies(
                        convert(externalCurrentExchangeRates.getValute())
                )
                .build();
    }

    private List<ExchangeRateQueueOut.Currency> convert(List<ValCurs.Valute> externalCurrencies) {
        return externalCurrencies.stream()
                .map(this::convert)
                .toList();
    }

    private ExchangeRateQueueOut.Currency convert(ValCurs.Valute externalCurrency) {
        return ExchangeRateQueueOut.Currency.builder()
                .currencyCode(externalCurrency.getCharCode())
                .currencyName(externalCurrency.getName())
                .nominal(externalCurrency.getNominal())
                .value(
                        convertToDouble(externalCurrency.getValue())
                )
                .build();
    }

    @SneakyThrows
    private Double convertToDouble(String toConvert) {
        NumberFormat format;
        Number number;
        try {
            format = NumberFormat.getNumberInstance(Locale.FRANCE);
            number = format.parse(toConvert);
        } catch (ParseException e) {
            format = NumberFormat.getNumberInstance(Locale.US);
            number = format.parse(toConvert);
        }

        return number.doubleValue();
    }
}
