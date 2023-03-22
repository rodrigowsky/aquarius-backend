package com.rodrigowsky.aquarius.auth.authProviders;

import com.rodrigowsky.aquarius.auth.authObjects.OtpAuthentication;
import com.rodrigowsky.aquarius.services.OtpService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

//what spring security uses to auth users
@Service
@Transactional
public class OtpAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LogManager.getLogger();

    @Autowired
    private OtpService otpService;


    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        logger.warn("OTP AUTH PROVIDER" + authentication.getCredentials().toString());
        Integer code = Integer.valueOf(authentication.getCredentials().toString());
        logger.warn("OTP AUTH PROVIDER " + code + username);

        boolean result = otpService.check(username, code);

        if (result) {
            return new OtpAuthentication(username, code);
        } else {
            throw new BadCredentialsException("Bad credentials.");
        }
    }

    //sets this auth provider to be used by the OTP code Authentication type of Auth
    @Override
    public boolean supports(Class<?> aClass) {
        return OtpAuthentication.class.isAssignableFrom(aClass);
    }
}
