//package com.example.Ecommerce_backend.api.security;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//
//@Configuration
//public class webSecurityConfig {
//
//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http.csrf().disable().cors().disable();
//        http.authorizeHttpRequests().anyRequest().permitAll();
//        return  http.build();
//    }
//
//}
package com.example.Ecommerce_backend.api.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;

@Configuration
public class webSecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf(AbstractHttpConfigurer::disable); // Disabling CSRF
        http.cors(AbstractHttpConfigurer::disable); // Disabling CORS
        http.authorizeHttpRequests(authorize -> authorize
                .requestMatchers("/users").permitAll() // Allow access to the registration endpoint
                .requestMatchers("/users/login").permitAll()
                .anyRequest().authenticated() // All other requests require authentication
        );
        return http.build();
    }
}
