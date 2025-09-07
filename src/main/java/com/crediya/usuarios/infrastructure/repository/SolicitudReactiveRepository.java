package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.entity.SolicitudEntity;
import com.crediya.usuarios.infrastructure.dto.SolicitudDTO;
import org.springframework.data.r2dbc.repository.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface SolicitudReactiveRepository extends ReactiveCrudRepository<SolicitudEntity, Long> {

    @Query("""
        SELECT s.id, 
               s.monto_solicitado AS monto,
               s.plazo_solicitado AS plazo,
               u.correo_electronico AS email,
               CONCAT(u.nombres, ' ', u.apellidos) AS nombre,
               s.tipo_prestamo AS tipoPrestamo,
               s.estado AS estadoSolicitud,
               u.salario_base AS salarioBase,
               (SELECT COUNT(*) FROM solicitudes sa WHERE sa.cliente_id = u.id AND sa.estado = 'Aprobado') AS solicitudesAprobadas
        FROM solicitudes s
        JOIN usuarios u ON s.cliente_id = u.id
        WHERE (:estado IS NULL OR s.estado = :estado)
        LIMIT :limit OFFSET :offset
        """)
    Flux<SolicitudDTO> findSolicitudesFiltradas(String estado, int limit, int offset);
}
