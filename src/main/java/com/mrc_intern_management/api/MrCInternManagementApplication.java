package com.mrc_intern_management.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class MrCInternManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(MrCInternManagementApplication.class, args);
	}

	@GetMapping
	public String Hello(){
		return "Hello Spring Boot!";
	}

}
