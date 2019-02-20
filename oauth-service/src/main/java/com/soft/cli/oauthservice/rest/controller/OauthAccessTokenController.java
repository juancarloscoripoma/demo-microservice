package com.soft.cli.oauthservice.rest.controller;

import com.soft.cli.oauthservice.core.service.OauthAccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class OauthAccessTokenController {
    private final Logger log = LoggerFactory.getLogger(OauthAccessTokenController.class);

    private final OauthAccessTokenService oauthAccessTokenService;

    @Autowired
    public OauthAccessTokenController(OauthAccessTokenService oauthAccessTokenService) {
        this.oauthAccessTokenService = oauthAccessTokenService;
    }

    @RequestMapping("/logout-up")
    public void deletedLogout(Principal user){
        log.debug("close session ...");
        String userName = user.getName();
        oauthAccessTokenService.delete(userName);
    }
}
