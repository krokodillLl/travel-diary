package com.krokodillLl.traveldiary.exchangerate;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients("com.krokodillLl.traveldiary.exchangerate.external")
@EnableScheduling
public class TravelDiaryExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelDiaryExchangeRateApplication.class, args);

	}

}
