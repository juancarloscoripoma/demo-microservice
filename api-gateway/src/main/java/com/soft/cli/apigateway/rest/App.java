package com.soft.cli.apigateway.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class App {

    /*
    @Autowired
    @Lazy
    private MessageByLocaleService messageByLocaleService;
    */

    @RequestMapping("/apig")
    public String home(){
        return "config gateway api";
    }

    @RequestMapping("/language")
    public String language(){
        return "none";
        //return String.format(messageByLocaleService.getMessage("language")," => demo");
    }
}
