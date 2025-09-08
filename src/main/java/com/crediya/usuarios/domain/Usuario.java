package com.crediya.usuarios.domain;

import jakarta.validation.constraints.*;

public class Usuario {

    private Long id;

    @NotBlank(message = "El nombre no puede estar vacío")
    @Size(min = 2, max = 50, message = "El nombre debe tener entre 2 y 50 caracteres")
    private String nombres;

    @NotBlank(message = "Los apellidos no pueden estar vacíos")
    @Size(min = 2, max = 50, message = "Los apellidos deben tener entre 2 y 50 caracteres")
    private String apellidos;

    @NotBlank(message = "El correo electrónico no puede estar vacío")
    @Email(message = "El correo electrónico no es válido")
    @Pattern(
            regexp = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$",
            message = "El correo electrónico debe contener un formato válido"
    )
    private String correoElectronico;

    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 5, max = 100, message = "La dirección debe tener entre 5 y 100 caracteres")
    private String direccion;

    @NotBlank(message = "El teléfono no puede estar vacío")
    @Pattern(
            regexp = "^[0-9]{7,10}$",
            message = "El teléfono debe contener entre 7 y 10 dígitos numéricos"
    )
    private String telefono;

    @NotNull(message = "El salario base es obligatorio")
    @Min(value = 0, message = "El salario base debe ser mayor o igual a 0")
    private Double salarioBase;

    @NotBlank(message = "La fecha de nacimiento es obligatoria")
    @Pattern(
            regexp = "^\\d{4}-\\d{2}-\\d{2}$",
            message = "La fecha de nacimiento debe tener el formato yyyy-MM-dd"
    )
    private String fechaNacimiento;

    @NotBlank(message = "La contraseña no puede estar vacía")
    @Size(min = 6, message = "La contraseña debe tener al menos 6 caracteres")
    private String password;

    @NotBlank(message = "El rol no puede estar vacío")
    private String rol;

    // --- Getters y Setters ---
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getNombres() {
        return nombres;
    }
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }
    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }
    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public String getDireccion() {
        return direccion;
    }
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public Double getSalarioBase() {
        return salarioBase;
    }
    public void setSalarioBase(Double salarioBase) {
        this.salarioBase = salarioBase;
    }

    public String getFechaNacimiento() {
        return fechaNacimiento;
    }
    public void setFechaNacimiento(String fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

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
}
