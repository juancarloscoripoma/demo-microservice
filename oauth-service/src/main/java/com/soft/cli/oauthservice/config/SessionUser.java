package com.soft.cli.oauthservice.config;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class SessionUser {
    private String name;
    private Object credentials;
    private Object principal;
    private Boolean clientOnly;
    private Boolean authenticated;
    private Oauth2Request oauth2Request;
    private Object details;
    private List<String> authorities;

    @Getter
    @Setter
    @NoArgsConstructor
    @ToString
    public static class Oauth2Request{
        private String clientId;
        private List<String> scope;
        private List<String> resourceIds;
        private String grantType;
    }
}
