package com.krokodillLl.traveldiary.exchangerate.configuration;

import com.krokodillLl.traveldiary.exchangerate.external.model.generated.ValCurs;
import feign.Logger;
import feign.codec.Decoder;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.openfeign.support.ResponseEntityDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class FeignConfig {

    @Bean
    public Decoder getDecoder() {
        Decoder decoder = (response, type) -> {
            try {
                var context = JAXBContext.newInstance(ValCurs.class);
                var unmarshaller = context.createUnmarshaller();

                return unmarshaller.unmarshal(response.body().asInputStream());
            } catch (JAXBException e) {
                return new Decoder.Default().decode(response, type);
            }
        };

        return new ResponseEntityDecoder(decoder);
    }

    @Bean
    Logger.Level feignLoggerLevel() {
        return Logger.Level.HEADERS;
    }
}
