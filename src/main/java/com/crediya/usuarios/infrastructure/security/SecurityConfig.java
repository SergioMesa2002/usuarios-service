package com.crediya.usuarios.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)        // ❌ Desactivar CSRF (solo APIs REST)
                .formLogin(ServerHttpSecurity.FormLoginSpec::disable) // ❌ Sin formulario HTML
                .httpBasic(ServerHttpSecurity.HttpBasicSpec::disable) // ❌ Sin autenticación básica
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/v1/login").permitAll()   // Login sin autenticación
                        .pathMatchers("/api/usuarios").permitAll()   // Registro de usuarios sin token
                        .anyExchange().authenticated()               // Todo lo demás requiere JWT
                )
                .build();
    }
}
