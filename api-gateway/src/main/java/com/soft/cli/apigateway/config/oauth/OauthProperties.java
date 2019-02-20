package com.soft.cli.apigateway.config.oauth;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Getter
public class OauthProperties {

    @Value("${serviceOauth.login}")
    String login = "";
    @Value("${serviceOauth.refresh}")
    String refresh = "";
    @Value("${serviceOauth.logout}")
    String logout = "";
    @Value("${serviceOauth.account}")
    String account = "";
    @Value("${serviceOauth.errorOauth}")
    String errorOauth ="";
}
