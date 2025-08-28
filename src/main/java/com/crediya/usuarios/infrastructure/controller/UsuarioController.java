package com.crediya.usuarios.infrastructure.controller;

import com.crediya.usuarios.application.UsuarioService;
import com.crediya.usuarios.domain.Usuario;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    private final UsuarioService service;

    public UsuarioController(UsuarioService service) {
        this.service = service;
    }

    @PostMapping
    public Mono<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
        return service.registrarUsuario(usuario);
    }
}
