package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface SolicitudReactiveRepository
        extends ReactiveCrudRepository<SolicitudEntity, Long>, SolicitudReactiveRepositoryCustom {
}
