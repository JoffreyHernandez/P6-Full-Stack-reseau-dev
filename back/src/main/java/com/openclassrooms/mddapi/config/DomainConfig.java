package com.openclassrooms.mddapi.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@EntityScan("com.openclassrooms.mddapi")
@EnableJpaRepositories("com.openclassrooms.mddapi")
@EnableTransactionManagement
public class DomainConfig {
}