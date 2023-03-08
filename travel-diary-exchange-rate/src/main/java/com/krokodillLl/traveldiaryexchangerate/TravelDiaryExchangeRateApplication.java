package com.krokodillLl.traveldiaryexchangerate;

import com.krokodillLl.traveldiaryexchangerate.service.api.RabbitService;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TravelDiaryExchangeRateApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelDiaryExchangeRateApplication.class, args);
		new Runnable() {
			@Autowired
			private RabbitService rabbitService;

			@SneakyThrows
			@Override
			public void run() {
				Thread.sleep(5000);
				rabbitService.sendMessage("test rabbit message");
			}
		};

	}

}
