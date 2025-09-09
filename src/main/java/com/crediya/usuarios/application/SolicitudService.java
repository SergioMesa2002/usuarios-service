package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Solicitud;
import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import com.crediya.usuarios.infrastructure.repository.SolicitudReactiveRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

import java.time.LocalDateTime;

@Service
public class SolicitudService {

    private final SolicitudReactiveRepository repository;

    public SolicitudService(SolicitudReactiveRepository repository) {
        this.repository = repository;
    }

    // Método para registrar una solicitud (siempre como Pendiente)
    public Mono<Solicitud> registrarSolicitud(Solicitud solicitud) {
        SolicitudEntity entity = new SolicitudEntity(
                null,
                solicitud.getClienteId(),
                solicitud.getMontoSolicitado(),
                solicitud.getPlazoSolicitado(),
                solicitud.getTipoPrestamo(),
                "Pendiente",
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

    // Método para listar solicitudes filtradas y paginadas (DTO)
    public Flux<com.crediya.usuarios.infrastructure.dto.SolicitudDTO> listarSolicitudesFiltradas(String estado, int page, int size) {
        int offset = page * size;
        return repository.findSolicitudesFiltradas(estado, size, offset);
    }

    // 🔥 Método para actualizar el estado de una solicitud
    public Mono<Solicitud> actualizarEstado(Long id, String nuevoEstado) {
        return repository.findById(id)
                .flatMap(entity -> {
                    entity.setEstado(nuevoEstado);
                    return repository.save(entity);
                })
                .map(updated -> {
                    Solicitud domain = new Solicitud();
                    domain.setId(updated.getId());
                    domain.setClienteId(updated.getClienteId());
                    domain.setMontoSolicitado(updated.getMontoSolicitado());
                    domain.setPlazoSolicitado(updated.getPlazoSolicitado());
                    domain.setTipoPrestamo(updated.getTipoPrestamo());
                    domain.setEstado(updated.getEstado());
                    domain.setFechaCreacion(updated.getFechaCreacion());
                    return domain;
                });
    }
}
