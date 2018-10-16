package com.luciano.oauth.demoresource.config;

import org.springframework.boot.autoconfigure.security.oauth2.resource.AuthoritiesExtractor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.*;

public class CustomAuthoritiesExtractor implements AuthoritiesExtractor {

    @Override
    public List<GrantedAuthority> extractAuthorities(Map<String, Object> map) {
        List<GrantedAuthority> authoritiesExtracted = new ArrayList<>();
        Map<String, Object> request = (Map<String, Object>) map.get("oauth2Request");
        List<LinkedHashMap<String,String>> authorities = (List<LinkedHashMap<String,String>>) request.get("authorities");

        for (LinkedHashMap<String,String> itemList :authorities) {
            for (Map.Entry<String, String> entry : itemList.entrySet()) {
                authoritiesExtracted.add(new SimpleGrantedAuthority(entry.getValue()));
            }
        }

        return authoritiesExtracted;
    }
}
