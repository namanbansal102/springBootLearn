package com.learningBoot.demo.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;

import com.learningBoot.demo.service.UserServiceDetailIMPL;

@Configuration
public class SpringSecurityConfig {

    @Autowired
    private UserServiceDetailIMPL userSIMPL;

    // Configures HTTP security
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        System.out.println("Running SEcuirty Filter Chain Configuration");
        http
            .authorizeHttpRequests(authz -> authz
                .requestMatchers("/public/**").permitAll() // Allow public access
                .anyRequest().authenticated() // Authenticate other requests
            )
            .csrf().disable()
            .httpBasic() // Enable basic authentication
            ;// Disable CSRF for simplicity in testing
             // Enable form login
        return http.build();
    }

    // Password encoder bean
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // AuthenticationProvider bean
    @Bean
    public AuthenticationProvider authenticationProvider() {
        System.out.println("My Provider Function Called");
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(userSIMPL); // Set UserDetailsService
        provider.setPasswordEncoder(passwordEncoder()); // Set PasswordEncoder
        return provider;
    }

    // AuthenticationManager bean - uses AuthenticationConfiguration to get it
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
}
