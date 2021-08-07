package com.example.IdentityService;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;

@SpringBootApplication(exclude = { SecurityAutoConfiguration.class})
@EnableJpaRepositories
public class IdentityServiceApplication {

	public static void main(String[] args) {

		SpringApplication.run(IdentityServiceApplication.class, args);
	}

}
