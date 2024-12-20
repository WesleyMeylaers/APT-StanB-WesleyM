package com.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange ->
                        exchange.pathMatchers(HttpMethod.GET,"/user", "/artist", "/album", "/playlist")
                                .permitAll()  // Open toegang voor alle paden
                                .anyExchange()
                                .permitAll()  // Ook alle andere eindpunten open
                )
                .oauth2ResourceServer(oauth2->oauth2.jwt(Customizer.withDefaults()));

        return serverHttpSecurity.build();
    }
}