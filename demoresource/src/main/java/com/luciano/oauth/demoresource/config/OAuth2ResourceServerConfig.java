package com.luciano.oauth.demoresource.config;


import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.oauth2.resource.ResourceServerProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.ResourceServerTokenServices;
import org.springframework.security.oauth2.provider.token.TokenStore;
import org.springframework.security.oauth2.provider.token.store.JwtAccessTokenConverter;
import org.springframework.security.oauth2.provider.token.store.JwtTokenStore;

import java.io.IOException;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final static String resourceId = "resources";

    @Autowired
    private CustomAccessTokenConverter customAccessTokenConverter;

    @Autowired
    private ResourceServerProperties sso;

    @Bean
    public ResourceServerTokenServices myUserInfoTokenServices() {
        return new CustomUserInfoTokenServices(sso.getUserInfoUri(), sso.getClientId());
    }

    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
                .and().authorizeRequests().antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable()
                .anonymous().disable();
    }

    @Bean
    public TokenStore tokenStore() {
        return new JwtTokenStore(accessTokenConverter());
    }

    @Bean
    public JwtAccessTokenConverter accessTokenConverter() {
        JwtAccessTokenConverter converter = new JwtAccessTokenConverter();
        Resource resource = new ClassPathResource("public.txt");
        String publicKey = null;
        try {
            publicKey = IOUtils.toString(resource.getInputStream());
        } catch (final IOException e) {
            throw new RuntimeException(e);
        }
        converter.setVerifierKey(publicKey);
        converter.setAccessTokenConverter(customAccessTokenConverter);
        return converter;
    }

    @Bean
    @Primary
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices defaultTokenServices = new DefaultTokenServices();
        defaultTokenServices.setTokenStore(tokenStore());
        defaultTokenServices.setSupportRefreshToken(true);
        return defaultTokenServices;
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(resourceId).tokenServices(tokenServices());
    }
}

//.and().authorizeRequests().antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read') and hasAuthority('ADMIN')")
//.and()
//        .authorizeRequests()
//    antMatchers(HttpMethod.GET, "/**").access("#oauth2.hasScope('read')")
//        .antMatchers(HttpMethod.OPTIONS, "/**").access("#oauth2.hasScope('read')")
//        .antMatchers(HttpMethod.POST, "/**").access("#oauth2.hasScope('write')")
//        .antMatchers(HttpMethod.PUT, "/**").access("#oauth2.hasScope('write')")
//        .antMatchers(HttpMethod.PATCH, "/**").access("#oauth2.hasScope('write')")
//        .antMatchers(HttpMethod.DELETE, "/**").access("#oauth2.hasScope('write')");
