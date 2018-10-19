package com.luciano.oauth.democlient.config.autorizacao.impl;

import com.luciano.oauth.democlient.config.autorizacao.AutorizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

@Service
public class AutorizacaoServiceImpl implements AutorizacaoService {

    @Autowired
    @Qualifier("restTemplateClientCredentials")
    private OAuth2RestOperations restTemplateClientCredentials;

    @Override
    public OAuth2AccessToken getAutorizacao(HttpServletRequest request) {
        if (request == null) {
            return null;
        }
        final String authHeader = request.getHeader("Authorization");

        if (authHeader == null) {
            return null;
        }
        if (!authHeader.startsWith("Bearer")) {
            return null;
        }
        final String bearer = authHeader.substring(7);
        if (bearer.isEmpty()) {
            return null;
        }

        OAuth2AccessToken credentialsToken = restTemplateClientCredentials.getAccessToken();

        return credentialsToken;
    }
}
