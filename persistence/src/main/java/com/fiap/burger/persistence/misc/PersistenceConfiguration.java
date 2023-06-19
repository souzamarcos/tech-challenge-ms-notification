package com.fiap.burger.persistence.misc;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com.fiap.burger.persistence")
@EnableJpaAuditing
@EntityScan("com.fiap.burger.persistence")
@Configuration
public class PersistenceConfiguration {}
