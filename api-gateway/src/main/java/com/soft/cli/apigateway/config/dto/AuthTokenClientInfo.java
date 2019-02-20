package com.soft.cli.apigateway.config.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AuthTokenClientInfo {
    private String access_token;
    private String token_type;
    private String scope;
}

