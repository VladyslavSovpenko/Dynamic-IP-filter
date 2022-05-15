package com.example.hrtest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class HrtestApplication {

	public static void main(String[] args) {
		SpringApplication.run(HrtestApplication.class, args);
	}

}
