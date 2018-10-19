package com.luciano.oauth.democlient.config.autorizacao;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.common.OAuth2AccessToken;

import javax.servlet.http.HttpServletRequest;

public interface AutorizacaoService {

    OAuth2AccessToken getAutorizacao(HttpServletRequest request);
}
