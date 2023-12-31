package com.elijahukeme.onlinelearningappbackend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication(exclude = SecurityAutoConfiguration.class)
public class OnlineLearningAppBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnlineLearningAppBackendApplication.class, args);
	}

}
