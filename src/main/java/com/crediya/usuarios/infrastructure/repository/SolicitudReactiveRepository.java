package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SolicitudReactiveRepository extends ReactiveCrudRepository<SolicitudEntity, Long> {
}
