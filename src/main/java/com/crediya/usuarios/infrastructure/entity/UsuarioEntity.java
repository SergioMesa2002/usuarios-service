package com.crediya.usuarios.infrastructure.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Table;

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
    private String fechaNacimiento;
    private String password;
    private String rol;


    // Constructor vacío (requerido por Spring Data)
    public UsuarioEntity() {}

    // Constructor con todos los campos
    public UsuarioEntity(Long id, String nombres, String apellidos, String correoElectronico,
                         String direccion, String telefono, Double salarioBase, String fechaNacimiento,
                         String password, String rol) {
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


    // --- Getters y Setters ---

    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    public String getRol() {
        return rol;
    }
    public void setRol(String rol) {
        this.rol = rol;
    }

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

    public String getFechaNacimiento() { return fechaNacimiento; }
    public void setFechaNacimiento(String fechaNacimiento) { this.fechaNacimiento = fechaNacimiento; }
}
