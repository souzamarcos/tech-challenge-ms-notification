package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.CustomerGateway;
import com.fiap.burger.usecase.adapter.usecase.EmailUseCase;
import com.fiap.burger.usecase.adapter.usecase.NotificationUseCase;
import com.fiap.burger.usecase.usecase.DefaultEmailUseCase;
import com.fiap.burger.usecase.usecase.DefaultNotificationUseCase;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

import java.time.Duration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public EmailUseCase emailUseCase() {
        return new DefaultEmailUseCase();
    }
    @Bean
    public NotificationUseCase orderUseCase(CustomerGateway customerGateway, EmailUseCase emailUseCase) {
        return new DefaultNotificationUseCase(customerGateway, emailUseCase);
    }

    @Bean
    public RestTemplate restTemplate(RestTemplateBuilder builder) {
        return builder
            .setConnectTimeout(Duration.ofMillis(3000))
            .setReadTimeout(Duration.ofMillis(3000))
            .build();
    }
}
