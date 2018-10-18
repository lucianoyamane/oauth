package com.luciano.oauth.democlient.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    private OAuth2RestOperations restTemplate;


    @RequestMapping(method = RequestMethod.GET, value="/")
    public Map<String, Object> home() {
//        JsonNode teste = restTemplate.getForObject(resourceURI, JsonNode.class);
        Map<String, Object> teste = (Map<String, Object>) restTemplate.getAccessToken();
        return teste;
    }
}
