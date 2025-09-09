package ru.microservices.core.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@EntityScan(basePackages = {"ru.microservices.core"})
@ComponentScan(basePackages = {"ru.microservices.core"})
public class CoreConfiguration {
}
