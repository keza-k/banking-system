package com.example.demo.api.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
            .csrf().disable() // <-- classic style
            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/banking/**").permitAll()
                .anyRequest().permitAll()
            );
        return http.build();
    }
}
