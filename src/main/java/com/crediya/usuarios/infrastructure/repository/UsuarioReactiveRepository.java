package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioReactiveRepository extends ReactiveCrudRepository<UsuarioEntity, Long> {
    Mono<Boolean> existsByCorreoElectronico(String correoElectronico);
}
