package com.example.demo.api.config;

// import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
// import org.springframework.web.servlet.config.annotation.InterceptorRegistry;

public class WebConfig {

    // public void addInterceptors(InterceptorRegistry registry, HandlerInterceptor apiKeyInterceptor) {
    //     // System.err.println(">>> Registering API key interceptor...");
    //     registry.addPathPatterns("/banking/**");
    // }

    public void addCorsMappings(CorsRegistry registry) {
        // System.err.println(">>> Applying CORS configuration...");
        registry.addMapping("/**")
                .allowedOrigins("*")
                .allowedMethods("*")
                .allowedHeaders("*")
                .exposedHeaders("*");
                // .allowCredentials(false);
    }
}
