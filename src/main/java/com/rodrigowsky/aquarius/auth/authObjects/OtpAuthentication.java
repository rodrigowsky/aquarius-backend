package com.rodrigowsky.aquarius.auth.authObjects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

// the Authentication contract represents the authentication process for
//a request - spring security
// this auth object is used for the application to authenticates
//the user with their otp code
//will be used by OtpAuthenticationProvider

@Component
public class OtpAuthentication extends UsernamePasswordAuthenticationToken {

    // used the same class to extend because we treat the OTP as a password
    //used the same approach as user-pass auth to save some lines of code

    public OtpAuthentication() {
        super(null, null);
    }

    public OtpAuthentication(
            Object principal,
            Object credentials,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    public OtpAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }


}
