package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Solicitud;
import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import com.crediya.usuarios.infrastructure.repository.SolicitudReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;
import reactor.core.publisher.Flux;

@Service
public class SolicitudService {

    private final SolicitudReactiveRepository repository;

    public SolicitudService(SolicitudReactiveRepository repository) {
        this.repository = repository;
    }

    // Método para registrar una solicitud
    public Mono<Solicitud> registrarSolicitud(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity(
                null,
                solicitud.getClienteId(),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoSolicitado(),
                solicitud.getTipoPrestamo(),
                "Pendiente", // Siempre se guarda como Pendiente
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

    // Método para listar todas las solicitudes
    public Flux<Solicitud> listarSolicitudes() {
        return repository.findAll()
                .map(entity -> {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setId(entity.getId());
                    solicitud.setClienteId(entity.getClienteId());
                    solicitud.setMontoSolicitado(entity.getMontoSolicitado());
                    solicitud.setPlazoSolicitado(entity.getPlazoSolicitado());
                    solicitud.setTipoPrestamo(entity.getTipoPrestamo());
                    solicitud.setEstado(entity.getEstado());
                    solicitud.setFechaCreacion(entity.getFechaCreacion());
                    return solicitud;
                });
    }

    // Método para obtener una solicitud por ID
    public Mono<Solicitud> obtenerSolicitudPorId(Long id) {
        return repository.findById(id)
                .map(entity -> {
                    Solicitud solicitud = new Solicitud();
                    solicitud.setId(entity.getId());
                    solicitud.setClienteId(entity.getClienteId());
                    solicitud.setMontoSolicitado(entity.getMontoSolicitado());
                    solicitud.setPlazoSolicitado(entity.getPlazoSolicitado());
                    solicitud.setTipoPrestamo(entity.getTipoPrestamo());
                    solicitud.setEstado(entity.getEstado());
                    solicitud.setFechaCreacion(entity.getFechaCreacion());
                    return solicitud;
                });
    }
}
