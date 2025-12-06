package com.sinodal.CardGeneratorHenriqueSouzaDaSilva;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories
public class CardGeneratorHenriqueSouzaDaSilvaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CardGeneratorHenriqueSouzaDaSilvaApplication.class, args);
	}

}
