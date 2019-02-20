package com.soft.cli.oauthservice.rest.controller;

import com.soft.cli.oauthservice.config.SessionUser;
import com.soft.cli.oauthservice.core.entity.Account;
import com.soft.cli.oauthservice.core.entity.Authority;
import com.soft.cli.oauthservice.core.service.AccountService;
import com.soft.cli.oauthservice.rest.dto.AccountDTO;
import com.soft.cli.oauthservice.rest.rbc.RbcAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.provider.OAuth2Authentication;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.URISyntaxException;
import java.security.Principal;
import java.util.ArrayList;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/account")
public class AccountController {
    private final Logger log = LoggerFactory.getLogger(AccountController.class);
    private final AccountService accountService;

    @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @RequestMapping(
            method = RequestMethod.GET
    )
    public ResponseEntity<AccountDTO> get(Principal user){
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)user;
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setUserName(oAuth2Authentication.getName());
        accountDTO.setClientOnly(oAuth2Authentication.isClientOnly());
        accountDTO.setRoles(oAuth2Authentication.getAuthorities().stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        log.debug("Session user : {} ",oAuth2Authentication);
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @RequestMapping(
            method = RequestMethod.POST
    )
    public ResponseEntity<AccountDTO> create(@Valid @RequestBody RbcAccount rbcAccount){
        Account account = accountService.create(rbcAccount);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRoles(account.getAuthorities()
                .stream().map(Authority::getName).collect(Collectors.toList()));
        accountDTO.setUserName(account.getUserName());
        return new ResponseEntity<>(accountDTO, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/update",
            method = RequestMethod.PUT)
    public ResponseEntity<AccountDTO> update(@RequestBody RbcAccount rbcAccount) throws URISyntaxException {
        log.debug("RbcAccount : {} ",rbcAccount.toString());
        Account account =accountService.update(rbcAccount.getName(), rbcAccount);
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRoles(account.getAuthorities()
                .stream().map(Authority::getName).collect(Collectors.toList()));
        accountDTO.setUserName(account.getUserName());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission",method = RequestMethod.GET)
    public ResponseEntity<AccountDTO> getAllActives(Principal user){
        log.debug("get user session permission");
        Account account = accountService.findOnePermission(user.getName());
        AccountDTO accountDTO = new AccountDTO();
        accountDTO.setRoles(account.getAuthorities()
                .stream().map(Authority::getName).collect(Collectors.toList()));
        accountDTO.setUserName(account.getUserName());
        return new ResponseEntity<>(accountDTO, HttpStatus.OK);
    }

    @RequestMapping(value = "/permission/active",method = RequestMethod.GET)
    public ResponseEntity<Boolean> getAllPermissionActive(Principal user){
        log.debug("get user session permission");
        Boolean active = accountService.findOnePermissionActive(user.getName());
        return new ResponseEntity<>(active, HttpStatus.OK);
    }

    @RequestMapping(value = "/session",method = RequestMethod.GET)
    public ResponseEntity<SessionUser> getSessionDetails(Principal user){
        OAuth2Authentication oAuth2Authentication = (OAuth2Authentication)user;
        SessionUser sessionUser = new SessionUser();
        sessionUser.setAuthenticated(oAuth2Authentication.isAuthenticated());
        sessionUser.setAuthorities(oAuth2Authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toList()));
        sessionUser.setClientOnly(oAuth2Authentication.isClientOnly());
        sessionUser.setDetails(oAuth2Authentication.getDetails());
        sessionUser.setCredentials(oAuth2Authentication.getCredentials());
        sessionUser.setName(oAuth2Authentication.getName());
        SessionUser.Oauth2Request oauth2Request = new SessionUser.Oauth2Request();
        oauth2Request.setClientId(oAuth2Authentication.getOAuth2Request().getClientId());
        oauth2Request.setGrantType(oAuth2Authentication.getOAuth2Request().getGrantType());
        oauth2Request.setResourceIds(new ArrayList<>(oAuth2Authentication.getOAuth2Request().getResourceIds()));
        oauth2Request.setScope(new ArrayList<>(oAuth2Authentication.getOAuth2Request().getScope()));
        sessionUser.setOauth2Request(oauth2Request);
        sessionUser.setPrincipal(oAuth2Authentication.getPrincipal());
        return new ResponseEntity<>(sessionUser, HttpStatus.OK);
    }

}
