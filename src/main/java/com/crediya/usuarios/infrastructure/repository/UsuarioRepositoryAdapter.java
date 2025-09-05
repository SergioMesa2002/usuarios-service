package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.domain.UsuarioRepositoryPort;
import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.time.LocalDate;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioReactiveRepository reactiveRepository;

    public UsuarioRepositoryAdapter(UsuarioReactiveRepository reactiveRepository) {
        this.reactiveRepository = reactiveRepository;
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        UsuarioEntity entity = toEntity(usuario);
        return reactiveRepository.save(entity).map(this::toDomain);
    }

    @Override
    public Mono<Boolean> existsByCorreoElectronico(String correo) {
        return reactiveRepository.existsByCorreoElectronico(correo);
    }

    @Override
    public Mono<Usuario> findByCorreoElectronico(String correo) {
        return reactiveRepository.findByCorreoElectronico(correo)
                .map(this::toDomain);
    }

    // ------------------- Mappers -------------------

    private UsuarioEntity toEntity(Usuario usuario) {
        return new UsuarioEntity(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreoElectronico(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getSalarioBase(),
                usuario.getFechaNacimiento() != null ? LocalDate.parse(usuario.getFechaNacimiento()) : null, // 👈 String -> LocalDate
                usuario.getPassword(),
                usuario.getRol()
        );
    }

    private Usuario toDomain(UsuarioEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());
        usuario.setNombres(entity.getNombres());
        usuario.setApellidos(entity.getApellidos());
        usuario.setCorreoElectronico(entity.getCorreoElectronico());
        usuario.setDireccion(entity.getDireccion());
        usuario.setTelefono(entity.getTelefono());
        usuario.setSalarioBase(entity.getSalarioBase());
        usuario.setFechaNacimiento(
                entity.getFechaNacimiento() != null ? entity.getFechaNacimiento().toString() : null // 👈 LocalDate -> String
        );
        usuario.setPassword(entity.getPassword());
        usuario.setRol(entity.getRol());
        return usuario;
    }
}
