package com.gerarifa.persistence.configuration;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Configuration
@PropertySource(value = { "classpath:/persistence-application.properties", "classpath:/application.properties" },
        ignoreResourceNotFound = true)
@ComponentScan({ "com.gerarifa.persistence" })
@EntityScan(basePackages = "com.gerarifa.persistence.entity")
@EnableJpaRepositories(basePackages = { "com.gerarifa.persistence.repository" })
@EnableTransactionManagement
public class PersistenceConfiguration {
}
