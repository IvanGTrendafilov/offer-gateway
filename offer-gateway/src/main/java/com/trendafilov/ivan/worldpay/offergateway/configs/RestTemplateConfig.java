package com.trendafilov.ivan.worldpay.offergateway.configs;

import com.trendafilov.ivan.worldpay.offergateway.exceptions.handlers.GatewayResponseErrorHandler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class RestTemplateConfig {

    @Autowired
    private GatewayResponseErrorHandler gatewayResponseErrorHandler;

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(
        final RestTemplateBuilder restTemplateBuilder) {
        final RestTemplate
            restTemplate =
            restTemplateBuilder.build();
        restTemplate.setErrorHandler(gatewayResponseErrorHandler);
        return restTemplate;
    }
}
