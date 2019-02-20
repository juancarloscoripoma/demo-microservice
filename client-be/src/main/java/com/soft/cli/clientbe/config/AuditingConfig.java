package com.soft.cli.clientbe.config;

import com.soft.cli.clientbe.entity.auditing.AuditorAwareImpl;
import com.soft.cli.clientbe.entity.auditing.DateTimeProviderImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.auditing.DateTimeProvider;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@Configuration
@EnableJpaAuditing(auditorAwareRef = "auditorProvider", dateTimeProviderRef = "dateTimeProvider")
public class AuditingConfig {

    @Bean
    public AuditorAware<String> auditorProvider() {
        return new AuditorAwareImpl();
    }

    @Bean
    public DateTimeProvider dateTimeProvider() {
        return new DateTimeProviderImpl();
    }
}
