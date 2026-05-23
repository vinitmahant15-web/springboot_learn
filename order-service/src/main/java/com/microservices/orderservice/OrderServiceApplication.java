package com.microservices.orderservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

/**
 * Order Service - Microservice 3
 * Port: 8003
 * 
 * This service demonstrates inter-service communication.
 * It calls User Service and Product Service to validate orders.
 */
@SpringBootApplication
@EnableDiscoveryClient
public class OrderServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }

    /**
     * RestTemplate Bean for inter-service communication
     * Configured with LoadBalanced for Eureka service discovery
     */
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }

}
