package com.crediya.usuarios.infrastructure.controller;

import com.crediya.usuarios.application.UsuarioService;
import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.infrastructure.security.JwtUtil;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    private final UsuarioService usuarioService;
    private final JwtUtil jwtUtil;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public AuthController(UsuarioService usuarioService, JwtUtil jwtUtil) {
        this.usuarioService = usuarioService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping("/login")
    public Mono<Map<String, String>> login(@RequestBody Map<String, String> loginRequest) {
        String correo = loginRequest.get("correoElectronico");
        String password = loginRequest.get("password");

        return usuarioService.findByCorreo(correo)
                .flatMap(usuario -> {
                    if (passwordEncoder.matches(password, usuario.getPassword())) {
                        String token = jwtUtil.generateToken(usuario.getCorreoElectronico(), usuario.getRol());
                        return Mono.just(Map.of("token", token));
                    } else {
                        return Mono.error(new RuntimeException("Credenciales inválidas"));
                    }
                })
                .switchIfEmpty(Mono.error(new RuntimeException("Usuario no encontrado")));
    }
}
