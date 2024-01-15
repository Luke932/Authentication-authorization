package com.luke932.GestioneDipendenti.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {
    private final JWTAuthFilter jwtAT;


    @Autowired
    public SecurityConfig(JWTAuthFilter jwtAT) {
        this.jwtAT = jwtAT;
    }

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(c -> c.disable());
        http.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        // Configura le regole di autorizzazione
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/utenti/**").permitAll());
        http.authorizeHttpRequests(auth -> auth.requestMatchers("/auth/**").permitAll());

        // Aggiungi il filtro CORS prima del filtro JWT
        http.addFilterBefore(jwtAT, UsernamePasswordAuthenticationFilter.class);


        return http.build();
    }





    @Bean
    PasswordEncoder encoder(){
        return new BCryptPasswordEncoder(11);
    }
}
