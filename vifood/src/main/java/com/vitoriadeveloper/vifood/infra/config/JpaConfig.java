package com.vitoriadeveloper.vifood.infra.config;

import com.vitoriadeveloper.vifood.infra.adapters.repositories.CustomJpaAdapter;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(
        repositoryBaseClass = CustomJpaAdapter.class,
        basePackages = "com.vitoriadeveloper.vifood.infra.repositories"
)
public class JpaConfig {
}
