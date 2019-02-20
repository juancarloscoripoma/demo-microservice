package com.soft.cli.configserver.rest;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class App {

    @GetMapping("/configs")
    public String home(){
        return "config-server tsoft client";
    }
}
