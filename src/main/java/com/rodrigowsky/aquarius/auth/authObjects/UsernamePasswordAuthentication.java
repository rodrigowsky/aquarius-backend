package com.rodrigowsky.aquarius.auth.authObjects;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;
// the Authentication contract represents the authentication process for
//a request - spring security
// this auth object is used fot the application to authenticates
//the user with their username and password
//The Authentication contract in Spring Security not only represents a principal, it
//also adds information on whether the authentication process finishes, as well as a col-
//lection of authorities.
//will be used by AuthenticationProviderService
@Component
public class UsernamePasswordAuthentication extends UsernamePasswordAuthenticationToken {

    //NOTE: . If the Authentication object is not set as authenticated, and no exception is thrown during the
    //process, the AuthenticationManager tries to find a proper Authentication-
    //Provider object to authenticate the request.

    public UsernamePasswordAuthentication() {
        super(null, null);
    }


    // when you call the one with three parameters spring sets the Authentication
    //object as authenticated
    public UsernamePasswordAuthentication(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    // when you call the one with two parameters, the authentication instance remains
    //unauthenticated,
    public UsernamePasswordAuthentication(Object principal, Object credentials) {
        super(principal, credentials);
    }
}
