package com.desempenho.aulas.detranrj.api;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;

@Configuration
@OpenAPIDefinition(info = @io.swagger.v3.oas.annotations.info.Info(title = "API Desempenho das Aulas de Auto Escola do Rio de Janeiro", version = "v1"))
public class OpenApiConfig implements WebMvcConfigurer {
}
