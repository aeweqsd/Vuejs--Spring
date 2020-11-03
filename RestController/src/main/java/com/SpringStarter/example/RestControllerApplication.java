package com.SpringStarter.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RestControllerApplication {

	public static void main(String[] args) {
		SpringApplication.run(RestControllerApplication.class, args);
	}

}
