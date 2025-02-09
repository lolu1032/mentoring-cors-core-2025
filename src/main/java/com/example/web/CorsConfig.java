package com.example.web;

import com.example.cors.properties.CorsProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@EnableConfigurationProperties(CorsProperties.class)
public class CorsConfig implements WebMvcConfigurer {

    private final CorsProperties corsProperties;

    public CorsConfig(CorsProperties corsProperties) {
        this.corsProperties = corsProperties;
    }


    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var allowed = corsProperties.allowed();
        var exposed = corsProperties.exposed();
        var maxAge = corsProperties.maxAge();
//        WebMvcConfigurer.super.addCorsMappings(registry);
        registry.addMapping("/**")
                .allowedHeaders(allowed.headers())
                .allowedMethods(allowed.methods())
                .allowedOrigins(allowed.origins())
                .allowCredentials(allowed.credentials())
                .exposedHeaders(exposed.headers())
                .maxAge(maxAge);
    }
}
