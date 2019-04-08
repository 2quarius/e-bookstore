package com.sixplus.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class CorsConfig extends WebMvcConfigurerAdapter {
    @Override
    public void addCorsMappings(CorsRegistry registry){
        registry.addMapping("/login")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("POST")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600);
        registry.addMapping("/logout")
                .allowedOrigins("http://localhost:8081")
                .allowedMethods("GET")
                .allowedHeaders("*")
                .allowCredentials(false).maxAge(3600);
    }
}
