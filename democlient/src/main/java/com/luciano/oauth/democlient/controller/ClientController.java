package com.luciano.oauth.democlient.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.DefaultOAuth2AccessToken;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/client", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClientController {

    @Autowired
    @Qualifier("restTemplateClientCredentials")
    private OAuth2RestOperations restTemplateClientCredentials;


    @Autowired
    @Qualifier("restTemplatePassword")
    private OAuth2RestOperations restTemplatePassword;


    @RequestMapping(method = RequestMethod.GET, value="/client_credentials")
    public ResponseEntity<String> clientCredentials() {
//        JsonNode teste = restTemplate.getForObject(resourceURI, JsonNode.class);
        OAuth2AccessToken teste = restTemplateClientCredentials.getAccessToken();
        return ResponseEntity.ok().body(new Gson().toJson(teste));
    }


    @RequestMapping(method = RequestMethod.GET, value="/password")
    public ResponseEntity<String> password() {
//        JsonNode teste = restTemplate.getForObject(resourceURI, JsonNode.class);
        restTemplatePassword.getOAuth2ClientContext().getAccessTokenRequest().set("username", "admin");
        restTemplatePassword.getOAuth2ClientContext().getAccessTokenRequest().set("password", "admin");
        OAuth2AccessToken teste = restTemplatePassword.getAccessToken();
        return ResponseEntity.ok().body(new Gson().toJson(teste));
    }


    @RequestMapping(value="/find", method = RequestMethod.GET)
    public ResponseEntity<String> find(){
        return ResponseEntity.ok().body(new Gson().toJson("SUCESSO!!!"));
    }
}
