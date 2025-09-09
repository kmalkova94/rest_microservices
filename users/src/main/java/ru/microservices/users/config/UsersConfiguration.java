package ru.microservices.users.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = {"ru.microservices.core", "ru.microservices.users"})
@Import(value = {ru.microservices.core.config.CoreConfiguration.class})
@EnableJpaRepositories(basePackages = {"ru.microservices.core", "ru.microservices.users"})
public class UsersConfiguration {
}
