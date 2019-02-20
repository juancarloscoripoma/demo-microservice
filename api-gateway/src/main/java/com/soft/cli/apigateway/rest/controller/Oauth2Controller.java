package com.soft.cli.apigateway.rest.controller;

import com.soft.cli.apigateway.config.dto.AccountDTO;
import com.soft.cli.apigateway.config.dto.AuthTokenClientInfo;
import com.soft.cli.apigateway.config.dto.AuthTokenInfo;
import com.soft.cli.apigateway.config.dto.OauthCs;
import com.soft.cli.apigateway.core.service.Oauth2Service;
import com.soft.cli.common.exceptions.util.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/uaa")
public class Oauth2Controller {
    private final Logger log = LoggerFactory.getLogger(Oauth2Controller.class);

    private final Oauth2Service oauth2Service;

    @Autowired
    public Oauth2Controller(Oauth2Service oauth2Service) {
        this.oauth2Service = oauth2Service;
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthTokenInfo> getLogin(String client_id, String grant_type,
                                                  String password, String username,
                                                  @RequestHeader("authorization") String authorization
                                   ){
        log.debug("AuthTokenInfo data {} , {} , {} , {} , {} ",client_id,grant_type,password,username,authorization);
        Check.notEmpty(grant_type,"error.grandType", grant_type);
        Check.isTrue(grant_type.equals("password"),"error.grandTypeInvalid",grant_type);
        AuthTokenInfo authTokenInfo = oauth2Service
                .login(new OauthCs(client_id,grant_type,"",
                        "",Boolean.FALSE,password,username),authorization);
        return new ResponseEntity<AuthTokenInfo>(authTokenInfo, HttpStatus.OK);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout(@RequestHeader("Cookie") Map<String, String> cookies){
        String res = oauth2Service.logout(cookies);
        return new ResponseEntity<String>(res, HttpStatus.OK);
    }

    @GetMapping("/account")
    public ResponseEntity<AccountDTO> getAccount(@RequestHeader("Cookie") Map<String, String> cookies){
        AccountDTO accountDTO = oauth2Service.getAccount(cookies);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @GetMapping("/account/active/penalty")
    public ResponseEntity<Boolean> getAccountPenalty(@RequestHeader("Cookie") Map<String, String> cookies){
        Boolean accountPenalty = oauth2Service.getAccountPenalty(cookies);
        return new ResponseEntity<>(accountPenalty, HttpStatus.OK);
    }

    @RequestMapping(value = "/client", method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthTokenClientInfo> getClient(String client_id, String grant_type,
                                                         String client_secret,
                                                         @RequestHeader("authorization") String authorization
    ){
        log.debug("AuthTokenInfo data {} , {} , {} , {} ",client_id,grant_type,client_secret,authorization);
        Check.notEmpty(grant_type,"error.grandType", grant_type);
        Check.isTrue(grant_type.equals("client_credentials"),"error.grandTypeInvalid",grant_type);
        AuthTokenClientInfo authTokenClientInfo = oauth2Service
                .client(new OauthCs(client_id,grant_type,client_secret,
                        "",Boolean.TRUE,"",""),authorization);
        return new ResponseEntity<AuthTokenClientInfo>(authTokenClientInfo, HttpStatus.OK);
    }

    @RequestMapping(value = "/refreshToken", method = RequestMethod.POST
            ,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<AuthTokenInfo> getRefreshToken(String client_id, String grant_type,
                                                         String client_secret,
                                                         String refresh_token,
                                                         @RequestHeader("authorization") String authorization
    ){
        log.debug("AuthTokenInfo refresh_token data {} , {} , {} , {} ",client_id,grant_type,client_secret,refresh_token);
        Check.notEmpty(grant_type,"error.grandType", grant_type);
        Check.isTrue(grant_type.equals("refresh_token"),"error.grandTypeInvalid",grant_type);
        AuthTokenInfo authTokenClientInfo = oauth2Service
                .loginRefreshToken(new OauthCs(client_id,grant_type,client_secret,
                        refresh_token,Boolean.FALSE,"",""),authorization);
        return new ResponseEntity<AuthTokenInfo>(authTokenClientInfo, HttpStatus.OK);
    }

}
