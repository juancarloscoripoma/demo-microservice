package com.soft.cli.apigateway.rest.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/error")
public class ErrorRedirect {
    private final Logger log = LoggerFactory.getLogger(ErrorRedirect.class);

    @GetMapping("/oauth")
    public Map<String,String> oauthError(){
        Map<String,String> error = new HashMap<>();
        log.debug("ERROR Authenticate................");
        error.put("ERROR","Authenticate first please......");
        return error;
    }
}
