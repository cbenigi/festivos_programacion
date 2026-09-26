package co.edu.festivos.infraestructura.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI festivosOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("API REST de Días Festivos - Pascual Bravo")
                        .description("API REST desarrollada bajo Arquitectura Onion para el cálculo y gestión de días festivos oficiales.")
                        .version("1.0.0")
                        .contact(new Contact().name("I.U. Pascual Bravo - Programación Web")));
    }
}
