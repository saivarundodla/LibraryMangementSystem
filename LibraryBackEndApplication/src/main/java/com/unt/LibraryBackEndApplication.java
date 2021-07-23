package com.unt;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan("com.unt")
@EntityScan("com.unt.model")
@EnableJpaRepositories("com.unt.repository")
@SpringBootApplication
public class LibraryBackEndApplication {

	public static void main(String[] args) {
		SpringApplication.run(LibraryBackEndApplication.class, args);
	}

}