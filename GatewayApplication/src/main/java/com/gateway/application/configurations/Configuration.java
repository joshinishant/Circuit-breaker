package com.gateway.application.configurations;

import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

@org.springframework.context.annotation.Configuration
public class Configuration {

    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }


    @Bean
    public CircuitBreaker circuitBreaker(){
        Resilience4JCircuitBreakerFactory circuitBreakerFactory=new Resilience4JCircuitBreakerFactory();
        CircuitBreaker circuitBreaker=circuitBreakerFactory.create("employee");
        return circuitBreaker;
    }

}
