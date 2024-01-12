package com.fiap.burger.application.config;

import com.amazonaws.auth.DefaultAWSCredentialsProviderChain;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.services.sqs.AmazonSQSAsync;
import com.amazonaws.services.sqs.AmazonSQSAsyncClientBuilder;
import com.fiap.burger.usecase.adapter.gateway.PaymentGateway;
import com.fiap.burger.usecase.adapter.usecase.PaymentUseCase;
import com.fiap.burger.usecase.usecase.DefaultPaymentUseCase;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.cloud.aws.messaging.core.QueueMessagingTemplate;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

@Configuration
@EnableSqs
public class ApplicationConfiguration {
    @Value("${cloud.aws.region}")
    private String awsRegion;
    @Value("${cloud.aws.end-point.uri}")
    private String awsEndpointUri;

    @Bean
    public PaymentUseCase paymentUseCase(PaymentGateway paymentGateway) {
        return new DefaultPaymentUseCase(paymentGateway);
    }

    @Bean
    @Primary
    public AmazonSQSAsync amazonSQSAsync() {
        AwsClientBuilder.EndpointConfiguration endpoint = new AwsClientBuilder.EndpointConfiguration(awsEndpointUri, awsRegion);

        return AmazonSQSAsyncClientBuilder
            .standard()
            .withEndpointConfiguration(endpoint)
            .withCredentials(new DefaultAWSCredentialsProviderChain())
            .build();
    }

    @Bean
    public QueueMessagingTemplate queueMessagingTemplate(AmazonSQSAsync amazonSQSAsync) {
        return new QueueMessagingTemplate(amazonSQSAsync);
    }
}
