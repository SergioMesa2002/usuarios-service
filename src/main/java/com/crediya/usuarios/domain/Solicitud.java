package com.crediya.usuarios.domain;

import jakarta.validation.constraints.*;

public class Solicitud {

    private Long id;

    @NotNull(message = "El ID del cliente es obligatorio")
    private Long clienteId;

    @NotNull(message = "El monto solicitado es obligatorio")
    @Min(value = 100000, message = "El monto solicitado debe ser al menos 100,000")
    private Double montoSolicitado;

    @NotNull(message = "El plazo solicitado es obligatorio")
    @Min(value = 6, message = "El plazo mínimo es de 6 meses")
    @Max(value = 60, message = "El plazo máximo es de 60 meses")
    private Integer plazoSolicitado;

    @NotBlank(message = "El tipo de préstamo es obligatorio")
    @Pattern(
            regexp = "consumo|hipotecario|educativo",
            message = "El tipo de préstamo debe ser consumo, hipotecario o educativo"
    )
    private String tipoPrestamo;

    private String estado = "Pendiente de revisión"; // siempre inicializa en este estado

    // --- Getters y Setters ---
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
}
