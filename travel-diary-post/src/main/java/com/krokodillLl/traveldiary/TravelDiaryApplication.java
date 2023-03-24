package com.krokodillLl.traveldiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class TravelDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(TravelDiaryApplication.class, args);
	}

}
