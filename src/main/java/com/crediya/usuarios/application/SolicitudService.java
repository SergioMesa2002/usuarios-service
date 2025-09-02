package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Solicitud;
import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import com.crediya.usuarios.infrastructure.repository.SolicitudReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
public class SolicitudService {

    private final SolicitudReactiveRepository repository;

    public SolicitudService(SolicitudReactiveRepository repository) {
        this.repository = repository;
    }

    public Mono<Solicitud> registrarSolicitud(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity(
                null,
                solicitud.getClienteId(),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoSolicitado(),
                solicitud.getTipoPrestamo(),
                "Pendiente", // 👈 siempre se guarda como Pendiente
                LocalDateTime.now()
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
