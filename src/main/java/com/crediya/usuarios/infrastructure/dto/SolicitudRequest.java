package com.crediya.usuarios.infrastructure.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class SolicitudRequest {

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El monto es obligatorio")
    @Min(value = 1000, message = "El monto mínimo es 1000")
    private Double montoSolicitado;

    @NotNull(message = "El plazo es obligatorio")
    @Min(value = 1, message = "El plazo mínimo es 1 mes")
    private Integer plazoSolicitado;

    @NotBlank(message = "El tipo de préstamo es obligatorio")
    private String tipoPrestamo;

    // Getters y setters
    public Long getClienteId() { return clienteId; }
    public void setClienteId(Long clienteId) { this.clienteId = clienteId; }

    public Double getMontoSolicitado() { return montoSolicitado; }
    public void setMontoSolicitado(Double montoSolicitado) { this.montoSolicitado = montoSolicitado; }

    public Integer getPlazoSolicitado() { return plazoSolicitado; }
    public void setPlazoSolicitado(Integer plazoSolicitado) { this.plazoSolicitado = plazoSolicitado; }

    public String getTipoPrestamo() { return tipoPrestamo; }
    public void setTipoPrestamo(String tipoPrestamo) { this.tipoPrestamo = tipoPrestamo; }
}
