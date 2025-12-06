package com.vitoriadeveloper.vifood;

import com.vitoriadeveloper.vifood.infra.adapters.repositories.CustomJpaAdapter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(repositoryBaseClass = CustomJpaAdapter.class)
public class VifoodApplication {

	public static void main(String[] args) {
		SpringApplication.run(VifoodApplication.class, args);
	}

}
