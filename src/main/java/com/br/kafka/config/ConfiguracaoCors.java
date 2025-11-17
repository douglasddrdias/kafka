package com.br.kafka.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
@PropertySource("classpath:application.yaml")
@Slf4j
public class ConfiguracaoCors implements WebMvcConfigurer {
    @Value("${cors.originPatterns:default}")
    private String urlsAceitasCors;

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        var origensAceitas = urlsAceitasCors.split(",");
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedOrigins(origensAceitas)
                .allowCredentials(true)
        ;
    }

}
