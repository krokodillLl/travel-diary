package com.krokodillLl.traveldiary.exchangerate.external;

import com.krokodillLl.traveldiary.exchangerate.configuration.FeignConfig;
import com.krokodillLl.traveldiary.exchangerate.external.model.generated.ValCurs;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(
        name="cbr-exchange-rate",
        url = "${external.exchange-rate.url}",
        configuration = FeignConfig.class
)
public interface ExchangeRateRequester {

    @GetMapping("/")
    ValCurs getCurrentExchangeRates();
}
