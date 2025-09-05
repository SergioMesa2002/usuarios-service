package com.crediya.usuarios.infrastructure.controller;

import com.crediya.usuarios.application.UsuarioService;
import com.crediya.usuarios.infrastructure.security.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public Mono<ResponseEntity<Map<String, String>>> login(@RequestBody Map<String, String> loginRequest) {
        String correo = loginRequest.get("correoElectronico");
        String password = loginRequest.get("password");

        return usuarioService.findByCorreo(correo)
                .flatMap(usuario -> {
                    if (usuario.getPassword() == null) {
                        return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                .body(Map.of("error", "El usuario no tiene contraseña registrada")));
                    }
                    try {
                        if (passwordEncoder.matches(password, usuario.getPassword())) {
                            String token = jwtUtil.generateToken(
                                    usuario.getCorreoElectronico(),
                                    usuario.getRol()
                            );
                            return Mono.just(ResponseEntity.ok(Map.of("token", token)));
                        } else {
                            return Mono.just(ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                                    .body(Map.of("error", "Credenciales inválidas")));
                        }
                    } catch (Exception e) {
                        return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                .body(Map.of("error", "Error interno: " + e.getMessage())));
                    }
                })
                .switchIfEmpty(Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                        .body(Map.of("error", "Usuario no encontrado"))))
                .onErrorResume(e -> Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                        .body(Map.of("error", "Excepción: " + e.getMessage()))));
    }

}
