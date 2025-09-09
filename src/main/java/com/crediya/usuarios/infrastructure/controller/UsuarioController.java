//package com.crediya.usuarios.infrastructure.controller;
//
//import com.crediya.usuarios.application.UsuarioService;
//import com.crediya.usuarios.domain.Usuario;
//import io.swagger.v3.oas.annotations.Operation;
//import io.swagger.v3.oas.annotations.media.Content;
//import io.swagger.v3.oas.annotations.media.Schema;
//import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import io.swagger.v3.oas.annotations.responses.ApiResponses;
//import jakarta.validation.Valid;
//import org.springframework.web.bind.annotation.*;
//import reactor.core.publisher.Mono;
//
//@RestController
//@RequestMapping("/api/usuarios")
//public class UsuarioController {
//
//    private final UsuarioService service;
//
//    public UsuarioController(UsuarioService service) {
//        this.service = service;
//    }
//
//    @Operation(
//            summary = "Registrar un nuevo usuario",
//            description = "Permite registrar un usuario en el sistema enviando sus datos personales. "
//                    + "Valida que los campos requeridos no estén vacíos y que el correo electrónico no se repita."
//    )
//    @ApiResponses(value = {
//            @ApiResponse(responseCode = "201", description = "Usuario registrado exitosamente",
//                    content = @Content(mediaType = "application/json",
//                            schema = @Schema(implementation = Usuario.class))),
//            @ApiResponse(responseCode = "400", description = "Error de validación en los datos enviados",
//                    content = @Content),
//            @ApiResponse(responseCode = "409", description = "El correo electrónico ya está registrado",
//                    content = @Content),
//            @ApiResponse(responseCode = "500", description = "Error interno en el servidor",
//                    content = @Content)
//    })
//    @PostMapping
//    public Mono<Usuario> registrar(@Valid @RequestBody Usuario usuario) {
//        return service.registrarUsuario(usuario);
//    }
//}
