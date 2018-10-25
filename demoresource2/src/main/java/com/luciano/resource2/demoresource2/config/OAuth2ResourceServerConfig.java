package com.luciano.resource2.demoresource2.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableResourceServer;
import org.springframework.security.oauth2.config.annotation.web.configuration.ResourceServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.ResourceServerSecurityConfigurer;

@Configuration
@EnableResourceServer
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class OAuth2ResourceServerConfig extends ResourceServerConfigurerAdapter {

    private final static String resourceId = "resources";



    @Override
    public void configure(HttpSecurity http) throws Exception {
        http.requestMatchers()
                .antMatchers("/**")
                .and().authorizeRequests().anyRequest().authenticated()
                .and().csrf().disable()
                .anonymous().disable();
    }

    @Override
    public void configure(ResourceServerSecurityConfigurer resources){
        resources.resourceId(resourceId);
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
