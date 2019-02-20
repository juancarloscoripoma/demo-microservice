package com.soft.cli.oauthservice.rest.controller;

import org.apache.commons.collections.map.HashedMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class UserController {
    private final Logger log = LoggerFactory.getLogger(UserController.class);
    @PreAuthorize("ROLE_USER")
    @RequestMapping("/username")
    public Map<String,String> getName(Principal user){
        Map<String,String> userName = new HashMap<>();
        userName.put("userName",user.getName());
        log.debug("user : {} ",user.getName());
        return userName;
    }

    @RequestMapping("/user")
    public Principal user(Principal user) {
        log.debug("session : {} ",user);
        return user;
    }
}
