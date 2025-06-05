package com.champs.downmidia.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    @SuppressWarnings("null")
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
            .allowedOrigins(
                "http://127.0.0.1:5500",                // desenvolvimento local
                "https://champs-8.github.io"            // GitHub Pages
            )
            .allowedMethods("GET", "POST", "PUT", "DELETE")
            .allowedHeaders("*");
    }
}
// Esta configuração permite que a API seja acessada de origens específicas,
// como o endereço do frontend durante o desenvolvimento.
