package com.crediya.usuarios.infrastructure.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.server.SecurityWebFilterChain;

@Configuration
public class SecurityConfig {

    private final JwtUtil jwtUtil;

    public SecurityConfig(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    // ✅ Bean para encriptar y validar contraseñas
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // ✅ Bean de AuthenticationManager basado en JWT
    @Bean
    public JwtAuthenticationManager jwtAuthenticationManager() {
        return new JwtAuthenticationManager(jwtUtil);
    }

    // ✅ Bean de SecurityContextRepository basado en JWT
    @Bean
    public JwtSecurityContextRepository jwtSecurityContextRepository(JwtAuthenticationManager jwtAuthenticationManager) {
        return new JwtSecurityContextRepository(jwtAuthenticationManager);
    }

    // ✅ Configuración de seguridad principal
    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http,
                                                         JwtAuthenticationManager jwtAuthenticationManager,
                                                         JwtSecurityContextRepository securityContextRepository) {
        return http
                .csrf(ServerHttpSecurity.CsrfSpec::disable)
                .authenticationManager(jwtAuthenticationManager)
                .securityContextRepository(securityContextRepository)
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/auth/**").permitAll() // login y register libres
                        .anyExchange().authenticated()        // todo lo demás requiere JWT
                )
                .build();
    }
}
