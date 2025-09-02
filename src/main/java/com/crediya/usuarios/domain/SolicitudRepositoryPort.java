package com.crediya.usuarios.domain;

import reactor.core.publisher.Mono;

public interface SolicitudRepositoryPort {
    Mono<Solicitud> save(Solicitud solicitud);
}
