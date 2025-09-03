package com.crediya.usuarios.infrastructure.controller;

import com.crediya.usuarios.application.SolicitudService;
import com.crediya.usuarios.domain.Solicitud;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;
import reactor.core.publisher.Flux;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

    // Endpoint para registrar una solicitud
    @PostMapping
    public Mono<ResponseEntity<Object>> registrarSolicitud(@RequestBody Solicitud solicitud) {
        return solicitudService.registrarSolicitud(solicitud)
                .map(saved -> ResponseEntity
                        .status(HttpStatus.CREATED)
                        .body((Object) saved)) // 👈 casteamos a Object
                .onErrorResume(e -> {
                    ErrorResponse error = new ErrorResponse(
                            "Error al guardar la solicitud",
                            e.getMessage()
                    );
                    return Mono.just(
                            ResponseEntity
                                    .status(HttpStatus.BAD_REQUEST) // 👈 devolvemos 400
                                    .body((Object) error)
                    );
                });
    }

    // Endpoint para listar todas las solicitudes
    @GetMapping
    public Mono<ResponseEntity<Object>> listarSolicitudes() {
        return solicitudService.listarSolicitudes()
                .collectList()
                .map(solicitudes -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body((Object) solicitudes)) // Devolvemos una lista de solicitudes
                .onErrorResume(e -> {
                    ErrorResponse error = new ErrorResponse(
                            "Error al obtener las solicitudes",
                            e.getMessage()
                    );
                    return Mono.just(
                            ResponseEntity
                                    .status(HttpStatus.BAD_REQUEST) // 👈 devolvemos 400
                                    .body((Object) error)
                    );
                });
    }

    // Endpoint para obtener una solicitud por ID
    @GetMapping("/{id}")
    public Mono<ResponseEntity<Object>> obtenerSolicitudPorId(@PathVariable("id") Long id) {
        return solicitudService.obtenerSolicitudPorId(id)
                .map(solicitud -> ResponseEntity
                        .status(HttpStatus.OK)
                        .body((Object) solicitud)) // Devolvemos la solicitud encontrada
                .switchIfEmpty(Mono.just(
                        ResponseEntity.status(HttpStatus.NOT_FOUND) // 👈 si no se encuentra la solicitud
                                .body(new ErrorResponse("Solicitud no encontrada", "No existe una solicitud con ese ID"))
                ))
                .onErrorResume(e -> {
                    ErrorResponse error = new ErrorResponse(
                            "Error al obtener la solicitud por ID",
                            e.getMessage()
                    );
                    return Mono.just(
                            ResponseEntity
                                    .status(HttpStatus.BAD_REQUEST) // 👈 devolvemos 400
                                    .body((Object) error)
                    );
                });
    }

    // ✅ Clase interna correcta
    public static class ErrorResponse {
        private final String error;
        private final String detalle;

        public ErrorResponse(String error, String detalle) {
            this.error = error;
            this.detalle = detalle;
        }

        public String getError() {
            return error;
        }

        public String getDetalle() {
            return detalle;
        }
    }
}
