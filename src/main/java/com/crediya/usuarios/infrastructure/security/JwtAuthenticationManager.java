package com.crediya.usuarios.infrastructure.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.List;

public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private static final Logger logger = LoggerFactory.getLogger(JwtAuthenticationManager.class);

    private final JwtUtil jwtUtil;

    public JwtAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();
        logger.info("🔑 Validando token JWT...");

        if (jwtUtil.validateToken(authToken)) {
            String username = jwtUtil.getUsernameFromToken(authToken);
            logger.info("✅ Token válido para usuario: {}", username);

            return Mono.just(
                    new UsernamePasswordAuthenticationToken(
                            username,
                            null,
                            List.of(new SimpleGrantedAuthority("ROLE_USER"))
                    )
            );
        }

        logger.warn("❌ Token inválido o expirado");
        // 👇 En vez de lanzar excepción, devolvemos vacío → Spring Security responderá 401
        return Mono.empty();
    }
}
