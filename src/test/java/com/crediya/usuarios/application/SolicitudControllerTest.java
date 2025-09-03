package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Solicitud;
import com.crediya.usuarios.infrastructure.controller.SolicitudController;
import com.crediya.usuarios.application.SolicitudService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import org.springframework.http.ResponseEntity;
import static org.mockito.Mockito.*;

public class SolicitudControllerTest {

    @Mock
    private SolicitudService solicitudService;

    private SolicitudController solicitudController;
    private Solicitud solicitud;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        solicitudController = new SolicitudController(solicitudService); // Inicializamos el controlador
        solicitud = new Solicitud();
        solicitud.setClienteId(1L);
        solicitud.setMontoSolicitado(10000.0);
        solicitud.setPlazoSolicitado(12);
        solicitud.setTipoPrestamo("Personal");
        solicitud.setEstado("Pendiente");
    }

    @Test
    public void testRegistrarSolicitudError() {
        // Simulamos que el servicio lanza una excepción
        when(solicitudService.registrarSolicitud(solicitud)).thenReturn(Mono.error(new RuntimeException("Error al registrar la solicitud")));

        // Llamamos al método del controlador
        Mono<ResponseEntity<Object>> response = solicitudController.registrarSolicitud(solicitud);

        // Verificamos que se maneje el error correctamente
        StepVerifier.create(response)
                .expectNextMatches(entity -> entity.getStatusCode().is4xxClientError())
                .verifyComplete();
    }




    @Test
    public void testRegistrarSolicitud() {
        // Simulamos la respuesta del servicio
        when(solicitudService.registrarSolicitud(solicitud)).thenReturn(Mono.just(solicitud));

        // Llamamos al método del controlador
        Mono<ResponseEntity<Object>> response = solicitudController.registrarSolicitud(solicitud);

        // Verificamos la respuesta usando StepVerifier
        StepVerifier.create(response)
                .expectNextMatches(entity -> entity.getStatusCode().is2xxSuccessful() && entity.getBody().equals(solicitud))
                .verifyComplete();
    }
}


