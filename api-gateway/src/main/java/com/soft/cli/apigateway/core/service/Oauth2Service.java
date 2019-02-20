package com.soft.cli.apigateway.core.service;

import com.soft.cli.apigateway.config.dto.AccountDTO;
import com.soft.cli.apigateway.config.dto.AuthTokenClientInfo;
import com.soft.cli.apigateway.config.dto.AuthTokenInfo;
import com.soft.cli.apigateway.config.dto.OauthCs;

import java.util.Map;

public interface Oauth2Service {
    AuthTokenInfo login(OauthCs oauthCs, String authorization);
    AuthTokenInfo loginRefreshToken(OauthCs oauthCs, String authorization);
    String logout(Map<String, String> cookies);
    AccountDTO getAccount(Map<String, String> cookies);
    Boolean getAccountPenalty(Map<String, String> cookies);
    AuthTokenClientInfo client(OauthCs oauthCs, String authorization);
}
