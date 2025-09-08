package com.crediya.usuarios.infrastructure.security;

import org.springframework.security.authentication.ReactiveAuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import reactor.core.publisher.Mono;

import java.util.List;

public class JwtAuthenticationManager implements ReactiveAuthenticationManager {

    private final JwtUtil jwtUtil;

    public JwtAuthenticationManager(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    public Mono<Authentication> authenticate(Authentication authentication) {
        String authToken = authentication.getCredentials().toString();

        if (jwtUtil.validateToken(authToken)) {
            String username = jwtUtil.getUsernameFromToken(authToken);

            // Por ahora damos rol fijo "ROLE_USER"
            return Mono.just(new org.springframework.security.authentication.UsernamePasswordAuthenticationToken(
                    username,
                    null,
                    List.of(new SimpleGrantedAuthority("ROLE_USER"))
            ));
        }

        return Mono.empty();
    }
}
