package com.example.apigateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity serverHttpSecurity) {
        serverHttpSecurity
                .authorizeExchange(exchange -> 
                    // Open toegang voor de /user, /artist en /album routes
                    exchange.pathMatchers(HttpMethod.GET, "/user/**", "/artist/**", "/album/**")
                        .permitAll()
                        // Bescherm toegang tot /playlist en andere routes, alleen geauthenticeerde gebruikers mogen toegang krijgen
                        .pathMatchers("/playlist/**")
                        .authenticated()
                        .anyExchange()
                        .authenticated()  // Alle andere eindpunten moeten geauthenticeerd zijn
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                    .jwt(withDefaults())  // JWT configuratie voor resource server
                );
        return serverHttpSecurity.build();
    }
}
