package com.crediya.usuarios.infrastructure.repository;

import com.crediya.usuarios.domain.Usuario;
import com.crediya.usuarios.domain.UsuarioRepositoryPort;
import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class UsuarioRepositoryAdapter implements UsuarioRepositoryPort {

    private final UsuarioReactiveRepository repository;

    public UsuarioRepositoryAdapter(UsuarioReactiveRepository repository) {
        this.repository = repository;
    }

    @Override
    public Mono<Usuario> save(Usuario usuario) {
        // Construcción manual sin Lombok
        UsuarioEntity entity = new UsuarioEntity(
                usuario.getId(),
                usuario.getNombres(),
                usuario.getApellidos(),
                usuario.getCorreoElectronico(),
                usuario.getDireccion(),
                usuario.getTelefono(),
                usuario.getSalarioBase(),
                usuario.getFechaNacimiento()
        );

        return repository.save(entity)
                .map(saved -> {
                    Usuario nuevo = new Usuario();
                    nuevo.setId(saved.getId());
                    nuevo.setNombres(saved.getNombres());
                    nuevo.setApellidos(saved.getApellidos());
                    nuevo.setCorreoElectronico(saved.getCorreoElectronico());
                    nuevo.setDireccion(saved.getDireccion());
                    nuevo.setTelefono(saved.getTelefono());
                    nuevo.setSalarioBase(saved.getSalarioBase());
                    nuevo.setFechaNacimiento(saved.getFechaNacimiento());
                    return nuevo;
                });
    }

    @Override
    public Mono<Boolean> existsByCorreoElectronico(String correo) {
        return repository.existsByCorreoElectronico(correo);
    }
}
