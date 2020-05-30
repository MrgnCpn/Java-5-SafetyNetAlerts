package com.safetynet.safetynetalert;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SafetyNetAlertApplication {
	private static final Logger logger = LogManager.getLogger("SafetyNetAlertApplication");

	public static void main(String[] args) {
		logger.info("Application start");
		SpringApplication.run(SafetyNetAlertApplication.class, args);
		logger.info("Application running");
	}
}
