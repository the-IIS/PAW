package com.paw.trello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class SpringBootTrelloApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootTrelloApplication.class, args);
	}

}
