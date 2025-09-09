package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.dto.SolicitudDTO;
import reactor.core.publisher.Flux;

public interface SolicitudReactiveRepositoryCustom {
    Flux<SolicitudDTO> findSolicitudesFiltradas(String estado, int limit, int offset);
}
