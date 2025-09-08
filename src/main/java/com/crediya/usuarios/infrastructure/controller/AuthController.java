package com.crediya.usuarios.infrastructure.controller;

import com.crediya.usuarios.infrastructure.security.JwtUtil;
import com.crediya.usuarios.infrastructure.security.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtUtil jwtUtil;
    private final CustomUserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public AuthController(JwtUtil jwtUtil,
                          CustomUserDetailsService userDetailsService,
                          PasswordEncoder passwordEncoder) {
        this.jwtUtil = jwtUtil;
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    // ✅ Login con JSON (email + password)
    @PostMapping("/login")
    public Mono<ResponseEntity<?>> login(@RequestBody LoginRequest loginRequest) {
        return userDetailsService.findByUsername(loginRequest.getEmail()) // ojo: usamos email como "username"
                .flatMap((UserDetails userDetails) -> {
                    if (passwordEncoder.matches(loginRequest.getPassword(), userDetails.getPassword())) {
                        String token = jwtUtil.generateToken(userDetails);
                        return Mono.just(ResponseEntity.ok(new JwtResponse(token)));
                    } else {
                        return Mono.just(ResponseEntity.status(401).body("Credenciales inválidas"));
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(401).body("Usuario no encontrado")));
    }

    // ✅ Validar un token
    @GetMapping("/validate")
    public ResponseEntity<String> validateToken(@RequestParam String token) {
        if (jwtUtil.validateToken(token)) {
            return ResponseEntity.ok("Token válido ✅");
        }
        return ResponseEntity.status(401).body("Token inválido ❌");
    }

    // DTO interno para request
    static class LoginRequest {
        private String email;
        private String password;

        public String getEmail() { return email; }
        public void setEmail(String email) { this.email = email; }

        public String getPassword() { return password; }
        public void setPassword(String password) { this.password = password; }
    }

    // DTO interno para response
    static class JwtResponse {
        private String token;

        public JwtResponse(String token) { this.token = token; }

        public String getToken() { return token; }
        public void setToken(String token) { this.token = token; }
    }
}
