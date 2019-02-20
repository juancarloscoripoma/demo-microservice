package com.soft.cli.oauthservice.rest.controller;

import com.soft.cli.oauthservice.core.service.OauthClientDetailsService;
import com.soft.cli.oauthservice.rest.dto.OauthClientDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/oauthClient")
public class OauthClientDetailsController {
    private final Logger log = LoggerFactory.getLogger(OauthClientDetailsController.class);
    private final OauthClientDetailsService oauthClientDetailsService;

    public OauthClientDetailsController(OauthClientDetailsService oauthClientDetailsService) {
        this.oauthClientDetailsService = oauthClientDetailsService;
    }

    @RequestMapping(
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<OauthClientDetailsDTO>> getAll(){
        log.debug("get all");
        List<OauthClientDetailsDTO> oauthClientDetailsDTOS = oauthClientDetailsService.getAll();
        return ResponseEntity.ok(oauthClientDetailsDTOS);
    }

    @RequestMapping(value = "/{clientId}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<OauthClientDetailsDTO> get(@PathVariable String clientId){
        log.debug("get");
        OauthClientDetailsDTO oauthClientDetailsDTO = oauthClientDetailsService.getOne(clientId);
        return ResponseEntity.ok(oauthClientDetailsDTO);
    }

    @RequestMapping(
            method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<OauthClientDetailsDTO> create(@RequestBody OauthClientDetailsDTO oauthClientDetailsDTO){
        OauthClientDetailsDTO create = oauthClientDetailsService.create(oauthClientDetailsDTO);
        return ResponseEntity.ok(create);
    }

    @RequestMapping(
            value = "/{clientId}",
            method = RequestMethod.PUT
    )
    public ResponseEntity<Void> update(@PathVariable String clientId,
                                       @RequestBody OauthClientDetailsDTO oauthClientDetailsDTO){
        oauthClientDetailsService.update(oauthClientDetailsDTO);
        return ResponseEntity.noContent().build();
    }

}
