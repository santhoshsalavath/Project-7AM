package com.usa.fedral.gov.ssa;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class EnrollmentForSsnGenerationProjApplication {

	/**
	 * it is used to generate logger file
	 */
	//private static final Logger LOGGER = LoggerFactory.getLogger(EnrollmentForSsnGenerationProjApplication.class);
	public static void main(String[] args) {
		
		SpringApplication.run(EnrollmentForSsnGenerationProjApplication.class, args);
		//LOGGER.debug("--Application Started--");
	}

}
