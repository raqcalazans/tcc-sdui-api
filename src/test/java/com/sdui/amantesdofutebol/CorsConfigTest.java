package com.sdui.amantesdofutebol;

import org.junit.jupiter.api.Test;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.cors.CorsConfiguration;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CorsConfigTest {

    @Test
    void registersCorsMappingForAllRoutes() {
        ExposedCorsRegistry registry = new ExposedCorsRegistry();

        new CorsConfig().addCorsMappings(registry);

        Map<String, CorsConfiguration> mappings = registry.configurations();
        CorsConfiguration cors = mappings.get("/**");
        assertEquals("http://localhost:3000", cors.getAllowedOrigins().get(0));
        assertEquals("http://localhost:8080", cors.getAllowedOrigins().get(1));
        assertEquals(true, cors.getAllowCredentials());
    }

    private static class ExposedCorsRegistry extends CorsRegistry {
        Map<String, CorsConfiguration> configurations() {
            return getCorsConfigurations();
        }
    }
}
