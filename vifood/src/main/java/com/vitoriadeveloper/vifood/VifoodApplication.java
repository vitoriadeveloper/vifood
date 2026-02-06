package com.vitoriadeveloper.vifood;

import com.vitoriadeveloper.vifood.infra.adapters.repositories.CustomJpaAdapter;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.slf4j.Logger;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaAdapter.class)
public class VifoodApplication {
	private static final Logger log = LoggerFactory.getLogger(VifoodApplication.class);


	public static void main(String[] args) {
		SpringApplication.run(VifoodApplication.class, args);
		log.info("Aplicaçao iniciada com sucesso !!");
	}

}
