package com.krokodillLl.traveldiary.exchangerate;

import com.krokodillLl.traveldiary.exchangerate.queue.service.api.RabbitProducerService;
import lombok.SneakyThrows;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableFeignClients("com.krokodillLl.traveldiary.exchangerate.external")
@EnableScheduling
public class TravelDiaryExchangeRateApplication {

	public static void main(String[] args) {
		var context = SpringApplication.run(TravelDiaryExchangeRateApplication.class, args);
		var runnable = new Runnable() {

			@SneakyThrows
			@Override
			public void run() {
				Thread.sleep(5000);
				var rabbitService = context.getBean(RabbitProducerService.class);
				rabbitService.convertAndSend("test rabbit message");
			}
		};

		runnable.run();

	}

}
