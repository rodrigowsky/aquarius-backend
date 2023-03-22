package com.rodrigowsky.aquarius.config;

import com.rodrigowsky.aquarius.auth.authProviders.AuthenticationProviderService;
import com.rodrigowsky.aquarius.auth.authProviders.OtpAuthenticationProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;


@Configuration
public class ATM {

    @Autowired
    private AuthenticationProviderService authenticationProvider;

    @Autowired
    private OtpAuthenticationProvider otpProvider;



    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder =
                http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(authenticationProvider);
        authenticationManagerBuilder.authenticationProvider(otpProvider);
        return authenticationManagerBuilder.build();
    }

}
