package com.crediya.usuarios.infrastructure.security;

import com.crediya.usuarios.infrastructure.entity.UsuarioEntity;
import com.crediya.usuarios.infrastructure.repository.UsuarioRepository;
import org.springframework.security.core.userdetails.ReactiveUserDetailsService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;


@Service
public class CustomUserDetailsService implements ReactiveUserDetailsService {

    private final UsuarioRepository usuarioRepository;

    public CustomUserDetailsService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Mono<UserDetails> findByUsername(String username) throws UsernameNotFoundException {
        return usuarioRepository.findByCorreoElectronico(username)
                .switchIfEmpty(Mono.error(new UsernameNotFoundException("Usuario no encontrado")))
                .map(this::mapToUserDetails);
    }
    private UserDetails mapToUserDetails(UsuarioEntity usuario) {
        System.out.println("Usuario: " + usuario.getCorreoElectronico() + " con rol: " + usuario.getRol());

        UserDetails details = User.builder()
                .username(usuario.getCorreoElectronico())
                .password(usuario.getPassword())
                .authorities(new SimpleGrantedAuthority("ROLE_" + usuario.getRol()))
                .build();

        System.out.println("Authorities: " + details.getAuthorities());

        return details;
    }


}
