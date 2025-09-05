package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.domain.UsuarioRepositoryPort;
import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioReactiveRepository reactiveRepository;

    public UsuarioRepositoryAdapter(UsuarioReactiveRepository reactiveRepository) {
        this.reactiveRepository = reactiveRepository;
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        UsuarioEntity entity = new UsuarioEntity(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreoElectronico(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getSalarioBase(),
                usuario.getFechaNacimiento(),
                usuario.getPassword(),
                usuario.getRol()
        );
        return reactiveRepository.save(entity)
                .map(this::toDomain);
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

    private Usuario toDomain(UsuarioEntity entity) {
        Usuario usuario = new Usuario();
        usuario.setId(entity.getId());
        usuario.setNombres(entity.getNombres());
        usuario.setApellidos(entity.getApellidos());
        usuario.setCorreoElectronico(entity.getCorreoElectronico());
        usuario.setDireccion(entity.getDireccion());
        usuario.setTelefono(entity.getTelefono());
        usuario.setSalarioBase(entity.getSalarioBase());
        usuario.setFechaNacimiento(entity.getFechaNacimiento());
        usuario.setPassword(entity.getPassword());
        usuario.setRol(entity.getRol());
        return usuario;
    }
}
