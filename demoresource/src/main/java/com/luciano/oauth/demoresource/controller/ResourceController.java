package com.luciano.oauth.demoresource.controller;

import com.google.gson.Gson;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.provider.authentication.OAuth2AuthenticationDetails;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping(value = "/resources", produces = MediaType.APPLICATION_JSON_VALUE)
public class ResourceController {

    @RequestMapping(value="/find", method = RequestMethod.GET)
    @PreAuthorize("#oauth2.hasScope('read')")
    public ResponseEntity<String> find(){
        return ResponseEntity.ok().body(new Gson().toJson("SUCESSO!!!"));
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

}
