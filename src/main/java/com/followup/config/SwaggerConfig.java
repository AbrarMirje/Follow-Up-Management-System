package com.followup.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI customerOpenAPI(){
        return new OpenAPI()
                .info(new Info()

                        .title("Follow Up Application Documentation")
                        .description("API documentation for Follow Up Application")
                        .contact(new Contact()
                                .name("codecrafterservices")
                                .email("info@code-crafters.in")
                                .url("https://code-crafter.in")
                        )
                );
    }
}
