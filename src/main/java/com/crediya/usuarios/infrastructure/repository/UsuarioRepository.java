package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Mono;

public interface UsuarioRepository extends ReactiveCrudRepository<UsuarioEntity, Long> {
    Mono<UsuarioEntity> findByCorreoElectronico(String correoElectronico);
}
