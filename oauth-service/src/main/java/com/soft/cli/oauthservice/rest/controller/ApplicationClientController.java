package com.soft.cli.oauthservice.rest.controller;

import com.soft.cli.oauthservice.core.entity.ApplicationClient;
import com.soft.cli.oauthservice.core.service.ApplicationClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/applicationClient")
public class ApplicationClientController {
    private final Logger log = LoggerFactory.getLogger(ApplicationClientController.class);
    private final ApplicationClientService applicationClientService;

    public ApplicationClientController(ApplicationClientService applicationClientService) {
        this.applicationClientService = applicationClientService;
    }

    @RequestMapping(value = "/ui-true",method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationClient>> getAllByTrue(){
        log.debug("getAllByTrue");
        return ResponseEntity.ok(applicationClientService.findAllByUiTrue());
    }

    @RequestMapping(value = "/all",method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationClient>> getAll(){
        log.debug("getAll");
        return ResponseEntity.ok(applicationClientService.findAll());
    }

    @RequestMapping(value = "/ui-false",method = RequestMethod.GET)
    public ResponseEntity<List<ApplicationClient>> getAllByFalse(){
        log.debug("getAllByFalse");
        return ResponseEntity.ok(applicationClientService.findAllByUiFalse());
    }

    @RequestMapping(value = "/allPage",method = RequestMethod.GET)
    public ResponseEntity<Page<ApplicationClient>> getAllPage(@PageableDefault Pageable pageable){
        log.debug("getAll");
        return ResponseEntity.ok(applicationClientService.findAllPage(pageable));
    }

}
