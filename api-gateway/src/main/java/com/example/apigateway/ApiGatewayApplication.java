package com.example.apigateway;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
public class ApiGatewayApplication {

    public static void main(String[] args) {
        // Load .env variables
        Dotenv dotenv = Dotenv.configure().load();

        // Make environment variables available to Spring
        dotenv.entries().forEach(entry -> System.setProperty(entry.getKey(), entry.getValue()));

        SpringApplication.run(ApiGatewayApplication.class, args);
    }
}
