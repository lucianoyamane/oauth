package com.luciano.oauth.demooauth.config;

import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

@Component
public class CustomAuthenticationProvider implements AuthenticationProvider {

    @Override
    public Authentication authenticate(Authentication authentication)
            throws AuthenticationException {

        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = null;

        Map<String, Object> properties = (LinkedHashMap<String, Object>) authentication.getDetails();
        String authorization = String.valueOf(properties.get("Authorization"));
        if (authorization.contains("invalido")) {
            throw new
                    BadCredentialsException("Acesso negado!!!");
        } else if (authorization.contains("valido")) {
            usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
                    "admin", "admin", new ArrayList<>());
        }
        return usernamePasswordAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(
                UsernamePasswordAuthenticationToken.class);
    }
}
