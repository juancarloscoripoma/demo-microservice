package com.soft.cli.oauthservice.rest.controller;

import com.soft.cli.oauthservice.core.entity.Permission;
import com.soft.cli.oauthservice.core.service.PermissionService;
import com.soft.cli.oauthservice.rest.rbc.RbcPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/permissions")
public class PermissionController {
    private final Logger log = LoggerFactory.getLogger(PermissionController.class);
    private final PermissionService permissionService;

    @Autowired
    public PermissionController(PermissionService permissionService) {
        this.permissionService = permissionService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<List<Permission>> getAllActives(){
        log.debug("Permission All");
        List<Permission> permissions = permissionService.findAllActives();
        return new ResponseEntity<>(permissions, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Permission> create(@Valid @RequestBody RbcPermission rbcPermission){
        log.debug("Created Permission : {} ",rbcPermission);
        Permission permission = permissionService.create(rbcPermission);
        return new ResponseEntity<>(permission, HttpStatus.CREATED);
    }

}
