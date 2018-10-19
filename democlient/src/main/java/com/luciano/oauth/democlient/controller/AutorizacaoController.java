package com.luciano.oauth.democlient.controller;

import com.google.gson.Gson;
import com.luciano.oauth.democlient.config.autorizacao.AutorizacaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.common.OAuth2AccessToken;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping(value = "/autorizacao", produces = MediaType.APPLICATION_JSON_VALUE)
public class AutorizacaoController {

    @Autowired
    private AutorizacaoService autorizacaoService;

    @RequestMapping(method = RequestMethod.GET, value="/")
    public ResponseEntity<String> clientCredentials(HttpServletRequest req) {
        OAuth2AccessToken resultado = autorizacaoService.getAutorizacao(req);
        if (resultado == null) {
            return ResponseEntity.badRequest().body(new Gson().toJson("Acesso negado"));
        }

        return ResponseEntity.ok().body(new Gson().toJson(resultado));
    }

    @RequestMapping(value="/find", method = RequestMethod.GET)
    public ResponseEntity<String> find(){
        return ResponseEntity.ok().body(new Gson().toJson("SUCESSO!!!"));
    }
}
