package com.soft.cli.clientbe.rest;

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
    @RequestMapping("/vi-pay")
    public String home(){
        return "api vi-pay";
    }

    @RequestMapping("/language")
    public String language(){
        return "hardcode none";
        //return messageByLocaleService.getMessage("language");
    }
}
