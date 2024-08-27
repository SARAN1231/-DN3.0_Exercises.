package com.saran.Bookstoreapi.Config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

@Configuration
@EnableWebSecurity
public class AppConfig {

    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.sessionManagement(sessionManagement -> sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(authorize -> authorize
                        .requestMatchers("/api/admin/**").hasAnyRole("RESTAURANT_OWNER", "ADMIN")
                        .requestMatchers("/api/**").authenticated()
                        .anyRequest().permitAll())
                .addFilterBefore(new com.saran.Bookstoreapi.Config.JwttokenValidator(), BasicAuthenticationFilter.class)
                .csrf(csrf -> csrf.disable())
                .cors(cors -> cors.configurationSource(corsConfigurationSource()));

        return http.build();
    }

    private CorsConfigurationSource corsConfigurationSource() {
        return new CorsConfigurationSource() {
            @Override
            public CorsConfiguration getCorsConfiguration(HttpServletRequest request) {
                CorsConfiguration cfg = new CorsConfiguration();
                cfg.setAllowCredentials(true);
                cfg.setAllowedOrigins(Arrays.asList( "http://localhost:3000","http://localhost:8080"));
                cfg.setAllowedMethods(Collections.singletonList("*"));
                cfg.setAllowedHeaders(Collections.singletonList("*"));
                cfg.setExposedHeaders(Arrays.asList("Authorization"));
                cfg.setMaxAge(3600L); // scroll down for details
                return cfg;
            }
        };
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}


// What is a Preflight Request?
//
//When you make certain types of requests from your front-end application (like using axios in React) to your back-end (like a Spring Boot server) that are considered "non-simple" (such as those using methods other than GET or POST, or those that include custom headers), the browser performs a special request first. This special request is called a preflight request, and it's an OPTIONS request sent to the server to ask for permission to make the actual request.
//Why Do We Use maxAge?
//
//The maxAge property tells the browser how long it can cache the result of this preflight request. If the preflight request says it's okay to make the actual request, the browser can remember this for a certain period (defined by maxAge), so it doesn't have to ask for permission again for every single request during this time period.
//What Does maxAge = 3600L Mean?
//
//Setting maxAge to 3600L means the browser can cache the permission to make the actual request for 3600 seconds (1 hour). During this hour, the browser won't need to send another preflight request for the same type of request to the server. This reduces the number of preflight requests and speeds up your application.