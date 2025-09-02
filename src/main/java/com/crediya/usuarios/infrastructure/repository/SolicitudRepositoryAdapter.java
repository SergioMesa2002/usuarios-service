package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.domain.Solicitud;
import com.crediya.usuarios.domain.SolicitudRepositoryPort;
import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class SolicitudRepositoryAdapter implements SolicitudRepositoryPort {

    private final SolicitudReactiveRepository repository;

    public SolicitudRepositoryAdapter(SolicitudReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Solicitud> save(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity(
                solicitud.getId(),
                solicitud.getClienteId(),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoSolicitado(),
                solicitud.getTipoPrestamo(),
                solicitud.getEstado(),
                solicitud.getFechaCreacion()
        );

        return repository.save(entity)
                .map(saved -> {
                    Solicitud domain = new Solicitud();
                    domain.setId(saved.getId());
                    domain.setClienteId(saved.getClienteId());
                    domain.setMontoSolicitado(saved.getMontoSolicitado());
                    domain.setPlazoSolicitado(saved.getPlazoSolicitado());
                    domain.setTipoPrestamo(saved.getTipoPrestamo());
                    domain.setEstado(saved.getEstado());
                    domain.setFechaCreacion(saved.getFechaCreacion());
                    return domain;
                });
    }

}
