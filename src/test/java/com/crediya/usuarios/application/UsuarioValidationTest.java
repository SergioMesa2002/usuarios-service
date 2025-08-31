package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Usuario;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import jakarta.validation.ValidatorFactory;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

public class UsuarioValidationTest {

    private Validator validator;

    @BeforeEach
    public void setUp() {
        ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
        validator = factory.getValidator();
    }

    @Test
    public void testNombreInvalido() {
        Usuario usuario = new Usuario();
        usuario.setNombres("A"); // demasiado corto

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("El nombre debe tener entre 2 y 50 caracteres"));
    }

    @Test
    public void testCorreoInvalido() {
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("correo_invalido"); // sin @

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("El correo electrónico debe contener un formato válido"));
    }

    @Test
    public void testDireccionCorta() {
        Usuario usuario = new Usuario();
        usuario.setDireccion("abc"); // demasiado corta

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("La dirección debe tener entre 5 y 100 caracteres"));
    }

    @Test
    public void testTelefonoInvalido() {
        Usuario usuario = new Usuario();
        usuario.setTelefono("123"); // muy corto

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("El teléfono debe contener entre 7 y 10 dígitos numéricos"));
    }

    @Test
    public void testSalarioNegativo() {
        Usuario usuario = new Usuario();
        usuario.setSalarioBase(-100.0); // salario inválido

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("El salario base debe ser mayor o igual a 0"));
    }

    @Test
    public void testFechaNacimientoInvalida() {
        Usuario usuario = new Usuario();
        usuario.setFechaNacimiento("31-12-2000"); // formato incorrecto

        Set<ConstraintViolation<Usuario>> violations = validator.validate(usuario);

        assertThat(violations)
                .anyMatch(v -> v.getMessage().equals("La fecha de nacimiento debe tener el formato yyyy-MM-dd"));
    }
}
