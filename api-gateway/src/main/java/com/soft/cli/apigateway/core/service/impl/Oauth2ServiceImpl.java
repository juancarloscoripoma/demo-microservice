package com.soft.cli.apigateway.core.service.impl;

import com.soft.cli.apigateway.config.ValidKeyAuthorizationServer;
import com.soft.cli.apigateway.config.dto.AccountDTO;
import com.soft.cli.apigateway.config.dto.AuthTokenClientInfo;
import com.soft.cli.apigateway.config.dto.AuthTokenInfo;
import com.soft.cli.apigateway.config.dto.OauthCs;
import com.soft.cli.apigateway.config.oauth.OauthProperties;
import com.soft.cli.apigateway.core.service.Oauth2Service;
import com.soft.cli.common.exceptions.util.Check;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class Oauth2ServiceImpl implements Oauth2Service {
    private final Logger log = LoggerFactory.getLogger(Oauth2ServiceImpl.class);
    private final RestTemplate restTemplate;
    private final ValidKeyAuthorizationServer validKeyAuthorizationServer;
    private final OauthProperties oauthProperties;

    @Autowired
    public Oauth2ServiceImpl(final RestTemplate restTemplate,
                             final ValidKeyAuthorizationServer validKeyAuthorizationServer,
                             final OauthProperties oauthProperties) {
        this.restTemplate = restTemplate;
        this.validKeyAuthorizationServer = validKeyAuthorizationServer;
        this.oauthProperties = oauthProperties;
    }

    /**
     * active when client id is distinct
     * Note what secret id is equals for all application
     * HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
     * .getHeadersWithClientCredentials(oauthCs.getClient_id()));
     */
    @Override
    public AuthTokenInfo login(OauthCs oauthCs, String authorization) {
        HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
                .getHeadersWithClientCredentialsAuthorization(authorization));
        ResponseEntity<AuthTokenInfo> responseEntity = restTemplate
                    .exchange(oauthProperties.getLogin()
                                    + oauthCs.getClient_id()
                                    + "&grant_type=" + oauthCs.getGrant_type()
                                    + "&password=" + oauthCs.getPassword()
                                    + "&username=" + oauthCs.getUsername(),
                            HttpMethod.POST, request, AuthTokenInfo.class);
            clientAccess(oauthCs.getClient_id(), oauthCs.getUsername(), responseEntity.getBody());
        if (responseEntity.getStatusCode().toString().equals("200")) {
            return responseEntity.getBody();
        }
        return null;
    }

    @Override
    public AuthTokenInfo loginRefreshToken(OauthCs oauthCs, String authorization) {
        log.debug("***************refresh token initial******************");
        HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
                .getHeadersWithClientCredentialsAuthorization(authorization));
        ResponseEntity<AuthTokenInfo> responseEntity = null;
        try {
            //balanced in service o micro service for many instances the micro service
            responseEntity = restTemplate
                    .exchange(oauthProperties.getLogin()
                                    + oauthCs.getClient_id()
                                    + "&grant_type=" + oauthCs.getGrant_type()
                                    + "&client_secret=" + oauthCs.getClient_secret()
                                    + "&refresh_token=" + oauthCs.getRefresh_token(),
                            HttpMethod.POST, request, AuthTokenInfo.class);
        } catch (HttpClientErrorException e) {
            log.debug("ERROR HttpClientErrorException {} ", e.getMessage());
            log.debug("ERROR State {} ", e.getStatusCode().value());
            Check.internal("error.internalUser", e);
        } catch (Exception e) {
            log.debug("ERROR Exception {} ", e.getMessage());
            Check.internal("error.internal", e);
        }
        if (responseEntity != null) if (responseEntity.getStatusCode().toString().equals("200")) {
            return responseEntity.getBody();
        }
        return null;
    }

    private void clientAccess(String clientId, String user, AuthTokenInfo info) {
        RestTemplate cgTemplate = restTemplate;
        HttpHeaders headers = new HttpHeaders();
        headers.set(HttpHeaders.AUTHORIZATION, info.getToken_type() + " " + info.getAccess_token());
        HttpEntity<String> entity = new HttpEntity<>("parameters", headers);
        String url = "http://config-general/api/roles/thereIsRoleByClient?login={user}&clientId={clientId}";
        Boolean access = cgTemplate.exchange(url, HttpMethod.GET, entity, Boolean.class, user, clientId).getBody();
        if (!access) {
            Check.code(HttpStatus.BAD_REQUEST);
        }
    }

    @Override
    public String logout(Map<String, String> cookies) {
        HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
                .getHeadersWithClientCredentialsCookies(cookies));
        ResponseEntity<String> responseEntity = null;
        try {
            responseEntity = restTemplate
                    .exchange(oauthProperties.getLogout(),
                            HttpMethod.POST, request, String.class);
        } catch (HttpClientErrorException e) {
            Check.internal("error.internal", e);
        } catch (Exception e) {
            Check.internal("error.internal", e);
        }
        if (responseEntity != null) if (responseEntity.getStatusCode().toString().equals("200")) {
            return responseEntity.getBody();
        }
        return null;
    }

    /**
     * balanced in service o micro service for many instances the micro service
     */
    @Override
    public AccountDTO getAccount(Map<String, String> cookies) {
        HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
                .getHeadersWithClientCredentialsCookies(cookies));
        ResponseEntity<AccountDTO> responseEntity = null;
        try {
            log.debug("URL GET ACCOUNT: {} ", oauthProperties.getAccount());
            responseEntity = restTemplate
                    .exchange(oauthProperties.getAccount(),
                            HttpMethod.GET, request, AccountDTO.class);
        } catch (HttpClientErrorException e) {
            Check.internal("error.internal", e);
        } catch (Exception e) {
            Check.internal("error.internal", e);
        }
        if (responseEntity != null) if (responseEntity.getStatusCode().toString().equals("200")) {
            log.debug("account : {} ", responseEntity.getBody());
            return responseEntity.getBody();
        }
        return null;
    }

    /**
     * balanced in service o micro service for many instances the micro service
     */
    @Override
    public Boolean getAccountPenalty(Map<String, String> cookies) {
        HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
                .getHeadersWithClientCredentialsCookies(cookies));
        ResponseEntity<Boolean> responseEntity = null;
        try {
            log.debug("URL GET ACCOUNT: {} ", oauthProperties.getAccount());
            responseEntity = restTemplate
                    .exchange(oauthProperties.getAccount() + "/permission/active",
                            HttpMethod.GET, request, Boolean.class);
        } catch (HttpClientErrorException e) {
            log.debug("URL GET ACTIVE HttpClientErrorException : {} ", e.getMessage());
            Check.internal("error.internal", e);
        } catch (Exception e) {
            log.debug("URL GET ACTIVE Exception : {} ", e.getMessage());
            Check.internal("error.internal", e);
        }
        if (responseEntity != null) if (responseEntity.getStatusCode().toString().equals("200")) {
            log.debug("account : {} ", responseEntity.getBody());
            return responseEntity.getBody();
        }
        return Boolean.FALSE;
    }

    @Override
    public AuthTokenClientInfo client(OauthCs oauthCs, String authorization) {
        HttpEntity<?> request = new HttpEntity<String>(validKeyAuthorizationServer
                .getHeadersWithClientCredentialsAuthorization(authorization));
        ResponseEntity<AuthTokenClientInfo> responseEntity = null;
        try {
            //balanced in service o micro service for many instances the micro service
            responseEntity = restTemplate
                    .exchange(oauthProperties.getLogin()
                                    + oauthCs.getClient_id()
                                    + "&grant_type=" + oauthCs.getGrant_type()
                                    + "&client_secret=" + oauthCs.getClient_secret(),
                            HttpMethod.POST, request, AuthTokenClientInfo.class);
        } catch (HttpClientErrorException e) {
            log.debug("ERROR HttpClientErrorException {} ", e.getMessage());
            log.debug("ERROR State {} ", e.getStatusCode().value());
            Check.internal("error.internal", e);
        } catch (Exception e) {
            log.debug("ERROR Exception {} ", e.getMessage());
            Check.internal("error.internal", e);
        }
        if (responseEntity != null) if (responseEntity.getStatusCode().toString().equals("200")) {
            return responseEntity.getBody();
        }
        return null;
    }
}
