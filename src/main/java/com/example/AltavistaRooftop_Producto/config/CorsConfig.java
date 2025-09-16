package com.example.AltavistaRooftop_Producto.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("*") // Permite todas las URLs del frontend. Puedes restringirlo si lo deseas.
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS") // Corrected method name here
                .allowedHeaders("*");
    }
}