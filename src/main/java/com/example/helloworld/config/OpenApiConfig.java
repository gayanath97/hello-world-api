package com.example.helloworld.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI helloWorldOpenApi() {
        return new OpenAPI()
                .info(new Info()
                        .title("Hello World API")
                        .description("Returns a greeting when the provided name starts with A–M or a–m.")
                        .version("1.0.0"));
    }
}
