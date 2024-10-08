package com.kingsCorner.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfiguration {
    private final JwtAuthenticationFilter jwtAuthenticationFilter;
    private final AuthenticationProvider authenticationProvider;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http)throws Exception{
         http
                    .csrf(AbstractHttpConfigurer::disable) // Consider enabling if needed
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers("/api/vi/auth/**").permitAll()
//                            .requestMatchers("/api/vi/admin/users").hasRole("ADMIN")  // New endpoint
//                            .requestMatchers("/api/vi/users/**").hasAnyRole("USER", "ADMIN") // Example role-based pattern
                            .anyRequest().authenticated()
                    )
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .authenticationProvider(authenticationProvider) // Configure your authentication provider
                    .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }
}

