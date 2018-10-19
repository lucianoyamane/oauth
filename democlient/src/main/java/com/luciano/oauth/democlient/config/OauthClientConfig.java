package com.luciano.oauth.democlient.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.DefaultOAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2ClientContext;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.client.OAuth2RestTemplate;
import org.springframework.security.oauth2.client.resource.OAuth2ProtectedResourceDetails;
import org.springframework.security.oauth2.client.token.AccessTokenRequest;
import org.springframework.security.oauth2.client.token.DefaultAccessTokenRequest;
import org.springframework.security.oauth2.client.token.grant.client.ClientCredentialsResourceDetails;
import org.springframework.security.oauth2.client.token.grant.password.ResourceOwnerPasswordResourceDetails;
import org.springframework.security.oauth2.common.AuthenticationScheme;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableOAuth2Client;

import java.util.ArrayList;
import java.util.List;

@EnableOAuth2Client
@Configuration
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OauthClientConfig {

    @Bean
    protected OAuth2ProtectedResourceDetails resourceClientCredentials() {

//        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();
//
//        List scopes = new ArrayList<String>(2);
//        scopes.add("read");
//        resource.setScope(scopes);
//
//        resource.setAccessTokenUri("http://localhost:8500/oauth/token");
//        resource.setClientId("password_code_client");
//        resource.setClientSecret("password_code_secret");
//        resource.setGrantType("password");
//
//        resource.setUsername("admin");
//        resource.setPassword("admin");
//        resource.setAuthenticationScheme(AuthenticationScheme.form);

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
    protected OAuth2ProtectedResourceDetails resourcePassword() {

        ResourceOwnerPasswordResourceDetails resource = new ResourceOwnerPasswordResourceDetails();

        List scopes = new ArrayList<String>();
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
    public OAuth2RestOperations restTemplateClientCredentials() {
        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resourceClientCredentials(), new DefaultOAuth2ClientContext(atr));
    }

    @Bean
    public OAuth2RestOperations restTemplatePassword(OAuth2ClientContext oauth2ClientContext) {
//        AccessTokenRequest atr = new DefaultAccessTokenRequest();

        return new OAuth2RestTemplate(resourcePassword(), oauth2ClientContext);
    }

}
