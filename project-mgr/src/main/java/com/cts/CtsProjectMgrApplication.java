package com.cts;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("com.cts.entities")
public class CtsProjectMgrApplication {

	public static void main(String[] args) {
		SpringApplication.run(CtsProjectMgrApplication.class, args);
	}
}

//@SpringBootApplication(exclude = {SecurityAutoConfiguration.class })
//public class CtsProjectMgrApplication extends SpringBootServletInitializer {
//
//	private static final Logger LOGGER = LogManager.getLogger(CtsProjectMgrApplication.class);
//
//	public static void main(String[] args) {
//		LOGGER.info("Spring boot application started");
//		SpringApplication.run(CtsProjectMgrApplication.class, args);
//	}
