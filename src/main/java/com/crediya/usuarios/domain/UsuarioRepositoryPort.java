package com.crediya.usuarios.domain;

import reactor.core.publisher.Mono;

public interface UsuarioRepositoryPort {
    Mono<Usuario> save(Usuario usuario);

    Mono<Boolean> existsByCorreoElectronico(String correo);

    // 👇 Nuevo método para login
    Mono<Usuario> findByCorreoElectronico(String correo);
}
