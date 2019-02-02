package com.steplabs.backend.vidtalk;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class VidtalkApplication {

	public static void main(String[] args) {
		SpringApplication.run(VidtalkApplication.class, args);
	}

}

