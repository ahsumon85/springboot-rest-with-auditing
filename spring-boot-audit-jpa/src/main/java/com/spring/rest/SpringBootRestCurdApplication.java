package com.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.spring.rest.audit.AuditorAwareImpl;

@SpringBootApplication
public class SpringBootRestCurdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestCurdApplication.class, args);
	}


}
