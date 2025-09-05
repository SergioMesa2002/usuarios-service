package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface UsuarioReactiveRepository extends ReactiveCrudRepository<UsuarioEntity, Long> {

    // Verifica si existe un usuario por correo
    Mono<Boolean> existsByCorreoElectronico(String correo);

    // Devuelve un usuario por correo (para login)
    @Query("SELECT * FROM usuarios WHERE correo_electronico = :correo LIMIT 1")
    Mono<UsuarioEntity> findByCorreoElectronico(String correo);
}
