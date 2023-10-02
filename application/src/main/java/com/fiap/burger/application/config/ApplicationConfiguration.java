package com.fiap.burger.application.config;

import com.fiap.burger.usecase.adapter.gateway.ClientGateway;
import com.fiap.burger.usecase.adapter.gateway.OrderGateway;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.gateway.ProductGateway;
import com.fiap.burger.usecase.adapter.usecase.ClientUseCase;
import com.fiap.burger.usecase.adapter.usecase.OrderUseCase;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.adapter.usecase.ProductUseCase;
import com.fiap.burger.usecase.usecase.DefaultClientUseCase;
import com.fiap.burger.usecase.usecase.DefaultOrderUseCase;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import com.fiap.burger.usecase.usecase.DefaultProductUseCase;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public ClientUseCase clientUseCase(ClientGateway repository) {
        return new DefaultClientUseCase(repository);
    }

    @Bean
    public OrderUseCase orderUseCase(OrderGateway orderGateway, ProductGateway productGateway, ClientGateway clientGateway) {
        return new DefaultOrderUseCase(orderGateway, productGateway, clientGateway);
    }

    @Bean
    public ProductUseCase productUseCase(ProductGateway productGateway) {
        return new DefaultProductUseCase(productGateway);
    }

    @Bean
    public PaymentUseCase paymentUseCase(PaymentGateway paymentGateway, OrderGateway orderGateway, ProductGateway productGateway, ClientGateway clientGateway) {
        return new DefaultPaymentUseCase(paymentGateway, new DefaultOrderUseCase(orderGateway, productGateway, clientGateway));
    }
}
