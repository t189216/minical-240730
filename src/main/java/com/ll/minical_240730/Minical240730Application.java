package com.ll.minical_240730;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class Minical240730Application {

	public static void main(String[] args) {
		SpringApplication.run(Minical240730Application.class, args);
	}

}
