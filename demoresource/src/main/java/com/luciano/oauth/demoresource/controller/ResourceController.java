package com.luciano.oauth.demoresource.controller;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.client.OAuth2RestOperations;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@RestController
@RequestMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {

    @Autowired
    @Qualifier("restTemplateClientCredentials")
    private OAuth2RestOperations restTemplateClientCredentials;

    @RequestMapping(value="/find", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('all')")
    public ResponseEntity<String> find(){
        return ResponseEntity.ok().body(new Gson().toJson("SUCESSO!!!"));
    }

    @RequestMapping(value = "/postcreate", method = POST)
    @PreAuthorize("#oauth2.hasScope('all')")
    public 	ResponseEntity<Object>  postcreate(@RequestBody Object body) {
        return new ResponseEntity(body,
                HttpStatus.OK);
    }

    @RequestMapping(value = "/getpathparam/{valorpathparam}", method = GET)
    @PreAuthorize("#oauth2.hasScope('all')")
    public 	ResponseEntity<Object>  getCargo(@PathVariable("valorpathparam") String valorpathparam) {
        return new ResponseEntity( valorpathparam,
                HttpStatus.OK);
    }

    @PreAuthorize("#oauth2.hasScope('read')")
    @RequestMapping(method = RequestMethod.GET, value = "/users/extra")
    @ResponseBody
    public Map<String, Object> getExtraInfo(Authentication auth) {
        OAuth2AuthenticationDetails oauthDetails
                = (OAuth2AuthenticationDetails) auth.getDetails();
        return (Map<String, Object>) oauthDetails
                .getDecodedDetails();
    }



    @RequestMapping(method = RequestMethod.GET, value="/client_credentials")
    public ResponseEntity<String> clientCredentials() {
        OAuth2AccessToken teste = restTemplateClientCredentials.getAccessToken();
        return ResponseEntity.ok().body(new Gson().toJson(teste));
    }

}
