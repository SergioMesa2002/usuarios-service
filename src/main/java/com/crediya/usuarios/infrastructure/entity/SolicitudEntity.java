package com.crediya.usuarios.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDateTime;

@Table("solicitudes")
public class SolicitudEntity {

    @Id
    private Long id;
    private Long clienteId;
    private Double montoSolicitado;
    private Integer plazoSolicitado;
    private String tipoPrestamo;
    private String estado;
    private LocalDateTime fechaCreacion;

    public SolicitudEntity() {}

    public SolicitudEntity(Long id, Long clienteId, Double montoSolicitado,
                           Integer plazoSolicitado, String tipoPrestamo,
                           String estado, LocalDateTime fechaCreacion) {
        this.id = id;
        this.clienteId = clienteId;
        this.montoSolicitado = montoSolicitado;
        this.plazoSolicitado = plazoSolicitado;
        this.tipoPrestamo = tipoPrestamo;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }

    // Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Double getMontoSolicitado() { return montoSolicitado; }
    public void setMontoSolicitado(Double montoSolicitado) { this.montoSolicitado = montoSolicitado; }

    public Integer getPlazoSolicitado() { return plazoSolicitado; }
    public void setPlazoSolicitado(Integer plazoSolicitado) { this.plazoSolicitado = plazoSolicitado; }

    public String getTipoPrestamo() { return tipoPrestamo; }
    public void setTipoPrestamo(String tipoPrestamo) { this.tipoPrestamo = tipoPrestamo; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}
