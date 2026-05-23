package com.microservices.userservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

/**
 * User Service - Microservice 1
 * 
 * This is the main entry point for the User Service microservice.
 * Port: 8001
 * 
 * @EnableDiscoveryClient - Registers this service with Eureka service discovery
 * - Other microservices can discover this service by name
 * - No need for hardcoded URLs
 */
@SpringBootApplication
@EnableDiscoveryClient
public class UserServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

}
