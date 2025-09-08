package com.crediya.usuarios.infrastructure.security;

import org.springframework.http.HttpHeaders;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.server.context.ServerSecurityContextRepository;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.server.WebFilterExchange;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;
import org.springframework.security.core.context.SecurityContextImpl;

public class JwtSecurityContextRepository implements ServerSecurityContextRepository {

    private final JwtAuthenticationManager jwtAuthenticationManager;

    public JwtSecurityContextRepository(JwtAuthenticationManager jwtAuthenticationManager) {
        this.jwtAuthenticationManager = jwtAuthenticationManager;
    }

    @Override
    public Mono<Void> save(ServerWebExchange exchange, SecurityContext context) {
        // No guardamos nada en sesión porque JWT es stateless
        return Mono.empty();
    }

    @Override
    public Mono<SecurityContext> load(ServerWebExchange exchange) {
        String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            String authToken = authHeader.substring(7);
            UsernamePasswordAuthenticationToken auth =
                    new UsernamePasswordAuthenticationToken(authToken, authToken);
            return jwtAuthenticationManager.authenticate(auth).map(SecurityContextImpl::new);
        }
        return Mono.empty();
    }
}
