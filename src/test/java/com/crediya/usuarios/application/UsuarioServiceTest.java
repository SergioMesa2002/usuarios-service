package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.domain.UsuarioRepositoryPort;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.mockito.Mockito.*;

public class UsuarioServiceTest {

    private UsuarioRepositoryPort usuarioRepository;
    private UsuarioService usuarioService;

    @BeforeEach
    public void setUp() {
        usuarioRepository = mock(UsuarioRepositoryPort.class);
        usuarioService = new UsuarioService(usuarioRepository);
    }

    @Test
    public void testRegistrarUsuario_CorreoNoRegistrado() {
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("test@mail.com");

        // Simulamos que el correo no está registrado
        when(usuarioRepository.existsByCorreoElectronico("test@mail.com")).thenReturn(Mono.just(false));
        when(usuarioRepository.save(usuario)).thenReturn(Mono.just(usuario));

        // Llamamos al servicio y verificamos el comportamiento
        Mono<Usuario> result = usuarioService.registrarUsuario(usuario);

        StepVerifier.create(result)
                .expectNext(usuario)
                .verifyComplete();

        // Verificamos que el repositorio haya intentado guardar el usuario
        verify(usuarioRepository, times(1)).save(usuario);
    }

    @Test
    public void testRegistrarUsuario_CorreoYaRegistrado() {
        Usuario usuario = new Usuario();
        usuario.setCorreoElectronico("test@mail.com");

        // Simulamos que el correo ya está registrado
        when(usuarioRepository.existsByCorreoElectronico("test@mail.com")).thenReturn(Mono.just(true));

        // Llamamos al servicio y esperamos una excepción
        Mono<Usuario> result = usuarioService.registrarUsuario(usuario);

        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        // Verificamos que el repositorio no haya guardado el usuario
        verify(usuarioRepository, never()).save(usuario);
    }
}
