package com.crediya.usuarios.infrastructure.config;

import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI apiDocConfig() {
        return new OpenAPI()
                .info(new Info()
                        .title("CrediYa - Usuarios API")
                        .description("Documentación de endpoints del microservicio de usuarios")
                        .version("1.0.0"));
    }
}
