package com.crediya.usuarios.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;
import java.time.LocalDate;

@Table("usuarios")
public class UsuarioEntity {

    @Id
    private Long id;
    private String nombres;
    private String apellidos;
    private String correoElectronico;
    private String direccion;
    private String telefono;
    private Double salarioBase;
    private LocalDate fechaNacimiento; // 👈 CAMBIO: LocalDate en lugar de String
    private String password;
    private String rol;

    // 🔹 Constructor vacío (Spring Data lo necesita)
    public UsuarioEntity() {}

    // 🔹 Constructor intermedio (8 parámetros, sin password ni rol)
    public UsuarioEntity(Long id, String nombres, String apellidos, String correoElectronico,
                         String direccion, String telefono, Double salarioBase,
                         LocalDate fechaNacimiento) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salarioBase = salarioBase;
        this.fechaNacimiento = fechaNacimiento;
    }

    // 🔹 Constructor completo (10 parámetros)
    public UsuarioEntity(Long id, String nombres, String apellidos, String correoElectronico,
                         String direccion, String telefono, Double salarioBase,
                         LocalDate fechaNacimiento, String password, String rol) {
        this.id = id;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.correoElectronico = correoElectronico;
        this.direccion = direccion;
        this.telefono = telefono;
        this.salarioBase = salarioBase;
        this.fechaNacimiento = fechaNacimiento;
        this.password = password;
        this.rol = rol;
    }

    // 🔹 Getters y setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getNombres() { return nombres; }
    public void setNombres(String nombres) { this.nombres = nombres; }

    public String getApellidos() { return apellidos; }
    public void setApellidos(String apellidos) { this.apellidos = apellidos; }

    public String getCorreoElectronico() { return correoElectronico; }
    public void setCorreoElectronico(String correoElectronico) { this.correoElectronico = correoElectronico; }

    public String getDireccion() { return direccion; }
    public void setDireccion(String direccion) { this.direccion = direccion; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public Double getSalarioBase() { return salarioBase; }
    public void setSalarioBase(Double salarioBase) { this.salarioBase = salarioBase; }

    public LocalDate getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(LocalDate fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }

    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }

    public String getRol() { return rol; }
    public void setRol(String rol) { this.rol = rol; }
}
