package com.vitoriadeveloper.vifood;

import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.slf4j.Logger;

@SpringBootApplication
public class VifoodApplication {
	private static final Logger log = LoggerFactory.getLogger(VifoodApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(VifoodApplication.class, args);
		log.info("Aplicaçao iniciada com sucesso!");
	}

}
