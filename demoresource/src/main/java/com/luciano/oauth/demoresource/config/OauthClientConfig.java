package com.luciano.oauth.demoresource.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
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
    protected OAuth2ProtectedResourceDetails resource() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = new ArrayList<String>(2);
        scopes.add("read");
        resource.setScope(scopes);

        resource.setAccessTokenUri("http://localhost:8500/oauth/token");
        resource.setClientId("password_code_client");
        resource.setClientSecret("password_code_secret");
        resource.setGrantType("password");

        resource.setUsername("admin");
        resource.setPassword("admin");


        return resource;
    }

    @Bean
    public OAuth2RestOperations restTemplate() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resource(), new DefaultOAuth2ClientContext(atr));
    }

}
