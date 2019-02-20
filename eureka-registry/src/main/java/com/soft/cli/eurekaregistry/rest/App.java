package com.soft.cli.eurekaregistry.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api")
public class App {

    @RequestMapping("/eureka")
    public String home(){
        return "config eureka registry";
    }
}
