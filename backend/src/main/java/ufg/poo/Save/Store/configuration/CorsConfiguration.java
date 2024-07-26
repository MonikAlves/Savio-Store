package ufg.poo.Save.Store.configuration;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Cors Configuration
 *
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    /**
     * Add cors mapping
     *
     * @param registry Cors registry
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("GET", "POST", "PUT", "DELETE")
                .allowedHeaders("*")
                .exposedHeaders("Authorization", "Cache-Control", "Content-Type")
                .allowCredentials(true)
                .maxAge(3600)
                .allowedOrigins("http://localhost:5173");
    }
}
