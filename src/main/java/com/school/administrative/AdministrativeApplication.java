package com.school.administrative;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "com.school.administrative")
public class AdministrativeApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdministrativeApplication.class, args);
	}

}
