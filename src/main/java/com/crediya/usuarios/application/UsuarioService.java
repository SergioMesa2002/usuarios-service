package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.domain.UsuarioRepositoryPort;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepositoryPort repository;

    public UsuarioService(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Usuario> registrarUsuario(Usuario usuario) {
        return repository.existsByCorreoElectronico(usuario.getCorreoElectronico())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new RuntimeException("El correo ya está registrado"));
                    } else {
                        return repository.save(usuario);
                    }
                });
    }
}
