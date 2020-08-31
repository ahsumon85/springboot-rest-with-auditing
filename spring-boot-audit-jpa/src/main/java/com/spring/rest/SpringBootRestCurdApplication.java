package com.spring.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;

import com.spring.rest.audit.AuditorAwareImpl;
import com.spring.rest.service.FileStorageProperties;

@SpringBootApplication
@EnableConfigurationProperties({
    FileStorageProperties.class
})
@EnableCaching
public class SpringBootRestCurdApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootRestCurdApplication.class, args);
	}


}
