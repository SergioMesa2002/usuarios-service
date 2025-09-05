package com.crediya.usuarios.application;

import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.domain.UsuarioRepositoryPort;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class UsuarioService {

    private final UsuarioRepositoryPort repository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UsuarioService(UsuarioRepositoryPort repository) {
        this.repository = repository;
    }

    public Mono<Usuario> registrarUsuario(Usuario usuario) {
        return repository.existsByCorreoElectronico(usuario.getCorreoElectronico())
                .flatMap(existe -> {
                    if (existe) {
                        return Mono.error(new RuntimeException("El correo ya está registrado"));
                    } else {
                        // encriptar contraseña antes de guardar
                        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
                        return repository.save(usuario);
                    }
                });
    }

    public Mono<Usuario> findByCorreo(String correo) {
        return repository.findByCorreoElectronico(correo);
    }
}
