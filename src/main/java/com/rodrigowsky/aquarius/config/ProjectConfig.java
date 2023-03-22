package com.rodrigowsky.aquarius.config;

import com.rodrigowsky.aquarius.filters.auth.InitialAuthenticationFilter;
import com.rodrigowsky.aquarius.filters.auth.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

@Configuration
public class ProjectConfig {

    @Autowired
    private InitialAuthenticationFilter initialAuthenticationFilter;

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

//    @Bean
//    public Logger getLogger() {
//        return LogManager.getLogger();
//    }


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
                .anyRequest()
                .authenticated();


            return http.build();
        }


}
