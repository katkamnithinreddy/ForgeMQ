package com.forgeMQ.forgeMQ;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling 
public class ForgeMqApplication {

	public static void main(String[] args) {
		SpringApplication.run(ForgeMqApplication.class, args);
	}

}
