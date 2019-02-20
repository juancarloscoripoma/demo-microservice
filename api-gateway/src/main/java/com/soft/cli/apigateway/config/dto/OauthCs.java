package com.soft.cli.apigateway.config.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OauthCs {
    private String client_id;
    private String grant_type;
    private String client_secret;
    private String refresh_token;
    private Boolean client;
    private String password;
    private String username;

    public OauthCs(String client_id, String grant_type, String client_secret, String refresh_token, Boolean client, String password, String username) {
        this.client_id = client_id;
        this.grant_type = grant_type;
        this.client_secret = client_secret;
        this.refresh_token = refresh_token;
        this.client = client;
        this.password = password;
        this.username = username;
    }

    public String toStringJson() {
        return "{" +
                "client_id=" + client_id +
                ", grant_type=" + grant_type +
                ", password=" + password +
                ", username=" + username +
                '}';
    }
}
