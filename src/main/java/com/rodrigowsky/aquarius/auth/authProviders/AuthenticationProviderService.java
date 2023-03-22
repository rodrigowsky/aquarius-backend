package com.rodrigowsky.aquarius.auth.authProviders;

import com.rodrigowsky.aquarius.model.CustomUserDetails;
import com.rodrigowsky.aquarius.services.JpaUserDetailsService;
import com.rodrigowsky.aquarius.services.OtpEmail;
import com.rodrigowsky.aquarius.services.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
//Auth Provider implements the authentication logic, is used by Spring Security Auth Manager
public class AuthenticationProviderService implements AuthenticationProvider {

    @Autowired
    // only function is to find the username from the db
    private JpaUserDetailsService userDetailsService;
    @Autowired
    // to check if the password matches
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    private OtpService otpService;

    @Autowired
    private OtpEmail otpEmailService;

    @Override
    //spring security uses this to auth users
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        CustomUserDetails user = (CustomUserDetails) userDetailsService.loadUserByUsername(username);
        return checkPassword(user, password, bCryptPasswordEncoder);

    }

    //sets this auth provider to be used by the User-Pass Authentication type of Auth
    @Override
    public boolean supports(Class<?> authentication) {
        return UsernamePasswordAuthenticationToken.class.isAssignableFrom(authentication);
    }

    //helper method to check password for auth method
    private Authentication checkPassword(CustomUserDetails user, String rawPassword, PasswordEncoder encoder) {
        if (encoder.matches(rawPassword, user.getPassword())) {
            setOtp(user);
            return new UsernamePasswordAuthenticationToken(user.getUsername(), user.getPassword(), user.getAuthorities());
            //send email here
        } else {
            throw new BadCredentialsException("Bad credentials");
        }
    }

    private void setOtp(CustomUserDetails u){
        otpService.renewOtp(u);
    }

    private void sendOtp(CustomUserDetails u){
        otpEmailService.sendEmail(u);
    }

}
