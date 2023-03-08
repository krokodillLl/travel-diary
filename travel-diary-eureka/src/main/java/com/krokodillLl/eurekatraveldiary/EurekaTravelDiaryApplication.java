package com.krokodillLl.eurekatraveldiary;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaTravelDiaryApplication {

	public static void main(String[] args) {
		SpringApplication.run(EurekaTravelDiaryApplication.class, args);
	}

}
