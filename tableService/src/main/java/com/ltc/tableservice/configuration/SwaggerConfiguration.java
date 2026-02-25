package com.ltc.tableservice.configuration;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class SwaggerConfiguration {
    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI().info(new Info()
                .title("Wedding Table Service System")
                .description("Welcome to Wedding Table Service System")
                .version("1.0.0")
                .contact(new Contact().name("Orkhan M.")));}
}
