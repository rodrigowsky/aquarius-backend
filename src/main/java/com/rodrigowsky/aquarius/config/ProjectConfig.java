package com.rodrigowsky.aquarius.config;

import com.rodrigowsky.aquarius.filters.InitialAuthenticationFilter;
import com.rodrigowsky.aquarius.filters.JwtAuthenticationFilter;
import com.rodrigowsky.aquarius.providers.AuthenticationProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {


    @Autowired
    private AuthenticationProviderService authenticationProvider;

    @Autowired
    private InitialAuthenticationFilter initialAuthenticationFilter;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;




    @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable();

//                .authorizeRequests().anyRequest().permitAll();
        http.addFilterAt(
                        initialAuthenticationFilter,
                        BasicAuthenticationFilter.class)
                .addFilterAfter(
                        jwtAuthenticationFilter,
                        BasicAuthenticationFilter.class
                );

        http.authorizeRequests()
                .anyRequest().authenticated();

            return http.build();
        }


}
