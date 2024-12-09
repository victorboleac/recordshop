package com.northcoders.recordshop;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class RecordshopApplication {

	public static void main(String[] args) {
		SpringApplication.run(RecordshopApplication.class, args);
	}

}
