package com.crediya.usuarios.infrastructure.dto;

public class SolicitudDTO {
    private Long id;
    private Double monto;
    private Integer plazo;
    private String email;
    private String nombre;
    private String tipoPrestamo;
    private String estadoSolicitud;
    private Double tasaInteres;          // 👈 faltaba
    private Double salarioBase;
    private Double deudaTotalMensual;    // 👈 faltaba
    private Integer solicitudesAprobadas;

    public SolicitudDTO(Long id,
                        Double monto,
                        Integer plazo,
                        String email,
                        String nombre,
                        String tipoPrestamo,
                        String estadoSolicitud,
                        Double tasaInteres,
                        Double salarioBase,
                        Double deudaTotalMensual,
                        Integer solicitudesAprobadas) {
        this.id = id;
        this.monto = monto;
        this.plazo = plazo;
        this.email = email;
        this.nombre = nombre;
        this.tipoPrestamo = tipoPrestamo;
        this.estadoSolicitud = estadoSolicitud;
        this.tasaInteres = tasaInteres;
        this.salarioBase = salarioBase;
        this.deudaTotalMensual = deudaTotalMensual;
        this.solicitudesAprobadas = solicitudesAprobadas;
    }



    // --- Getters y Setters ---
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Double getMonto() { return monto; }
    public void setMonto(Double monto) { this.monto = monto; }

    public Integer getPlazo() { return plazo; }
    public void setPlazo(Integer plazo) { this.plazo = plazo; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getTipoPrestamo() { return tipoPrestamo; }
    public void setTipoPrestamo(String tipoPrestamo) { this.tipoPrestamo = tipoPrestamo; }

    public String getEstadoSolicitud() { return estadoSolicitud; }
    public void setEstadoSolicitud(String estadoSolicitud) { this.estadoSolicitud = estadoSolicitud; }

    public Double getTasaInteres() { return tasaInteres; }
    public void setTasaInteres(Double tasaInteres) { this.tasaInteres = tasaInteres; }

    public Double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(Double salarioBase) { this.salarioBase = salarioBase; }

    public Double getDeudaTotalMensual() { return deudaTotalMensual; }
    public void setDeudaTotalMensual(Double deudaTotalMensual) { this.deudaTotalMensual = deudaTotalMensual; }

    public Integer getSolicitudesAprobadas() { return solicitudesAprobadas; }
    public void setSolicitudesAprobadas(Integer solicitudesAprobadas) { this.solicitudesAprobadas = solicitudesAprobadas; }
}
