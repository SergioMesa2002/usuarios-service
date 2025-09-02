package com.crediya.usuarios.infrastructure.controller;

import com.crediya.usuarios.application.SolicitudService;
import com.crediya.usuarios.domain.Solicitud;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/solicitudes")
public class SolicitudController {

    private final SolicitudService solicitudService;

    public SolicitudController(SolicitudService solicitudService) {
        this.solicitudService = solicitudService;
    }

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

    // ✅ Clase interna correcta
    static class ErrorResponse {
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
