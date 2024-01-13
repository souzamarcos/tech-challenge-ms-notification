package com.fiap.burger.application.config;

import com.fiap.burger.usecase.misc.profiles.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.aws.messaging.config.annotation.EnableSqs;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Test
@EnableSqs
@Configuration
public class AwsSQSConfigurationTest
{
    @Value("${cloud.aws.region}")
    private String awsRegion;
    @Value("${cloud.aws.end-point.uri}")
    private String awsEndpointUri;
}
