package com.soft.cli.apigateway.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import java.util.Collections;
import java.util.Map;

@Configuration
public class ValidKeyAuthorizationServer {
    private final Logger log = LoggerFactory.getLogger(ValidKeyAuthorizationServer.class);

    private HttpHeaders getHeaders(){
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        return headers;
    }

    public HttpHeaders getHeadersWithClientCredentialsAuthorization(String authorization){
        HttpHeaders headers = getHeaders();
        headers.add("Authorization", authorization);
        return headers;
    }

    public HttpHeaders getHeadersWithClientCredentialsCookies(Map<String,String> cookies){
        log.debug("SET COOKIES");
        HttpHeaders headers = getHeaders();
        cookies.forEach(headers::add);
        //headers.forEach((s, strings) -> log.debug(s+" == "+strings));
        return headers;
    }

}
