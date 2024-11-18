package com.wellnes360.Task_Management_System.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

         http.csrf(customizer-> customizer.disable());
         //Enables the H2 DB Console
         http.headers().frameOptions().disable();
         http.authorizeHttpRequests(request->request.anyRequest().authenticated());

         //Enabling Basic Form on web Page
        http.httpBasic(Customizer.withDefaults());

        //Enabling Basic Form for REST API - Postman else return HTML Page
        http.formLogin(Customizer.withDefaults());

        http.sessionManagement(session->session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        //Return Object of SecurityFilterChain
        return http.build();
    }
}
