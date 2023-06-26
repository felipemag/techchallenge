package com.fiap.techchallenge.fourlanches.adapter.driven.infra.data;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages = "com.fiap.techchallenge.fourlanches.adapter.driven.infra.data")
public class SpringJpaConfig {
}
