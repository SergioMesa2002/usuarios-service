package com.crediya.usuarios.infrastructure.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.security.web.server.authentication.AuthenticationWebFilter;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtAuthenticationFilter implements ServerSecurityContextRepository {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;

    public JwtAuthenticationFilter(JwtUtil jwtUtil, CustomUserDetailsService userDetailsService) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, org.springframework.security.core.context.SecurityContext context) {
        return Mono.empty(); // no persistimos el contexto
    }

    @Override
    public Mono<org.springframework.security.core.context.SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String token = authHeader.substring(7);

            String username = jwtUtil.extractUsername(token); // 👈 método que debes implementar en JwtUtil
            if (username != null) {
                return userDetailsService.findByUsername(username)
                        .map(userDetails -> {
                            UsernamePasswordAuthenticationToken authentication =
                                    new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                            return new SecurityContextImpl(authentication);
                        });
            }
        }
        return Mono.empty();
    }
}
