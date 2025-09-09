package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.infrastructure.dto.SolicitudDTO;
import org.springframework.r2dbc.core.DatabaseClient;
import reactor.core.publisher.Flux;

public class SolicitudReactiveRepositoryImpl implements SolicitudReactiveRepositoryCustom {

    private final DatabaseClient client;

    public SolicitudReactiveRepositoryImpl(DatabaseClient client) {
        this.client = client;
    }

    @Override
    public Flux<SolicitudDTO> findSolicitudesFiltradas(String estado, int limit, int offset) {
        String sql = """
            SELECT s.id, 
                   s.monto_solicitado AS monto,
                   s.plazo_solicitado AS plazo,
                   u.correo_electronico AS email,
                   CONCAT(u.nombres, ' ', u.apellidos) AS nombre,
                   s.tipo_prestamo AS tipoPrestamo,
                   IFNULL(s.estado, 'Pendiente') AS estadoSolicitud,
                   IFNULL(s.tasa_interes, 0.0) AS tasaInteres,
                   IFNULL(u.salario_base, 0.0) AS salarioBase,
                   IFNULL(u.deuda_total_mensual, 0.0) AS deudaTotalMensual,
                   (SELECT COUNT(*) 
                       FROM solicitudes sa 
                       WHERE sa.cliente_id = u.id 
                         AND sa.estado = 'Aprobado') AS solicitudesAprobadas
            FROM solicitudes s
            JOIN usuarios u ON s.cliente_id = u.id
            WHERE (? IS NULL OR s.estado = ?)
            LIMIT ? OFFSET ?
            """;

        return client.sql(sql)
                .bind(0, estado)
                .bind(1, estado)
                .bind(2, limit)
                .bind(3, offset)
                .map((row, metadata) -> new SolicitudDTO(
                        row.get("id", Long.class),
                        row.get("monto", Double.class),
                        row.get("plazo", Integer.class),
                        row.get("email", String.class),
                        row.get("nombre", String.class),
                        row.get("tipoPrestamo", String.class),
                        row.get("estadoSolicitud", String.class),
                        row.get("tasaInteres", Double.class),
                        row.get("salarioBase", Double.class),
                        row.get("deudaTotalMensual", Double.class),
                        row.get("solicitudesAprobadas", Integer.class)
                ))
                .all();
    }
}
