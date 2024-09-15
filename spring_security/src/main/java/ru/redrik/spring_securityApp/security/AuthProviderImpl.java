package ru.redrik.spring_securityApp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import ru.redrik.spring_securityApp.services.UserDetailsServiceMy;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {

    private final UserDetailsServiceMy userDetailsServiceMy;

    @Autowired
    public AuthProviderImpl(UserDetailsServiceMy userDetailsServiceMy) {
        this.userDetailsServiceMy = userDetailsServiceMy;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String username = authentication.getName();

        UserDetails userDetails = userDetailsServiceMy.loadUserByUsername(username);

        String password = authentication.getCredentials().toString();

        if(!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password");
        }

        return new UsernamePasswordAuthenticationToken(userDetailsServiceMy, password,
                Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
