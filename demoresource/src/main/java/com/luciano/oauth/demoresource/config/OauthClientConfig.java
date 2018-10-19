package com.luciano.oauth.demoresource.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

@EnableOAuth2Client
@Configuration
public class OauthClientConfig {

    @Bean
    protected OAuth2ProtectedResourceDetails resourceClientCredentials() {

        ClientCredentialsResourceDetails resource = new ClientCredentialsResourceDetails();
        List scopes = new ArrayList<String>();
        scopes.add("all");
        resource.setScope(scopes);

        resource.setAccessTokenUri("http://localhost:8500/oauth/token");
        resource.setClientId("authorization_code_client");
        resource.setClientSecret("authorization_code_secret");
        resource.setGrantType("client_credentials");


        return resource;
    }

    @Bean
    @Qualifier("restTemplateClientCredentials")
    public OAuth2RestOperations restTemplateClientCredentials(OAuth2ClientContext oauth2ClientContext) {
        return new OAuth2RestTemplate(resourceClientCredentials(), oauth2ClientContext);
    }

}
