package com.example.courserecommender.security;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.example.courserecommender.service.MyUserDetailsService;

/*
    --------------- MY NOTES -----------------

    - UsernamePasswordAuthenticationFilter : built in filter that processes authentication requests
        - typically handles login form submissions
        - checks username and password, and if valid, authenticates the user
        - part of Spring Security's authentication mechanism


    - cofnig classes are all the same style, classes annotated with @Configuration
        - contain methods annotated with @Bean
        - these methods define beans that are managed by Spring's IoC container

    - csrf ? 
        - Cross-Site Request Forgery
        - type of attack where unauthorized commands are transmitted from a user that the web application trusts
        - Spring Security enables CSRF protection by default
        - for stateless APIs, CSRF protection is often disabled for simplicity

    - is disabling csrf for APIs common ? 

Questions
    - should I 've placed this file in the config package, is the config package for general config or what ?
        answer : config package is for general configuration, security package is for security related stuff

    - Do I have to name this class SecurityConfig, or can it be any name ?
        answer : can be any name, but convention is to name it SecurityConfig

    - are the bean methods inside config classes gotta have known name or any name ? 
        answer : can be any name, but convention is to name it after what it returns, like securityFilterChain
                IoC container picks any @Bean with type SecurityFilterChain, so name doesn't matter     
 
 */

@Configuration
public class SecurityConfig {
    /*
     * we don't use it in the code , however, we need it to be injected by Spring
     * into the security filter chain, so we declare it as a dependency
     * why do we need it ? : to load user-specific data during authentication
     * for example, when a user tries to log in, Spring Security will use this
     * service
     * automatically to fetch user details (like username, password, roles) from
     * wherever they are stored
     * in this case, we have hardcoded a user in MyUserDetailsService
     */
    private MyUserDetailsService myUserDetailsService;

    @Autowired
    public SecurityConfig(MyUserDetailsService myUserDetailsService) {
        this.myUserDetailsService = myUserDetailsService;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        // create instance of your custom filter'
        // that is not DI , ValidationHeaderFilter is annotated with @Component
        ValidationHeaderFilter validationFilter = new ValidationHeaderFilter();
        http
                // is disabling csrf for APIs common ?
                .csrf(csrf -> csrf.disable())

                // secure endpoints based on roles
                .authorizeHttpRequests(auth -> auth
                        // matches any request that starts with /course/
                        .requestMatchers("/course/**").permitAll() // view course with id, permit all
                        .requestMatchers("/courses").permitAll() // view all courses, permit all
                        .requestMatchers("/{id}").authenticated() // only authorized , PUT / DELETE course
                        .requestMatchers("/").authenticated() // only authorized, POST add course
                        .anyRequest().denyAll() // block everything else
                )

                // enable HTTP Basic authentication
                .httpBasic(httpBasic -> {
                });

        // adding our custom filter, before the authentication filter
        http.addFilterBefore(validationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    // define a bean for password encoding , so you can inject it in
    // MyUserDetailsService

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        // Get the builder
        AuthenticationManagerBuilder authBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        // Configure the userDetailsService and passwordEncoder
        authBuilder.userDetailsService(myUserDetailsService).passwordEncoder(passwordEncoder());

        // Build and return the AuthenticationManager directly (no .and() needed)
        return authBuilder.build();
    }

}