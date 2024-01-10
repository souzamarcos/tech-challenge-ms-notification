package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public PaymentUseCase paymentUseCase(PaymentGateway paymentGateway) {
        return new DefaultPaymentUseCase(paymentGateway);
    }
}
