package com.soft.cli.oauthservice.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class OauthClientDetailsDTO {
    @Size(min = 2,max = 256)
    private String client;

    @Size(min = 2,max = 256)
    private String name;

    @Size(min = 2,max = 256)
    private String nameUi;

    @Size(min = 0,max = 256)
    private String clientSecret;

    @Size(min = 2, max = 256)
    private String scope;

    @Size(min = 2,max = 256)
    private String authorizedGrantTypes;

    @Size(min = 0,max = 256)
    private String webServerRedirectUrl;

    @Size(min = 2, max = 256)
    private String authorities;

    private Integer accessTokenValidity;

    private Integer refreshTokenValidity;

    @Size(min = 2,max = 4096)
    private String additionalInformation;

    @Size(min = 0, max = 256)
    private String autoApprove;

    private Boolean ui;

    public OauthClientDetailsDTO(String client, String name, String nameUi, String clientSecret,
                                 String scope, String authorizedGrantTypes, String webServerRedirectUrl,
                                 String authorities, Integer accessTokenValidity, Integer refreshTokenValidity,
                                 String additionalInformation, String autoApprove, Boolean ui) {
        this.client = client;
        this.name = name;
        this.nameUi = nameUi;
        this.clientSecret = clientSecret;
        this.scope = scope;
        this.authorizedGrantTypes = authorizedGrantTypes;
        this.webServerRedirectUrl = webServerRedirectUrl;
        this.authorities = authorities;
        this.accessTokenValidity = accessTokenValidity;
        this.refreshTokenValidity = refreshTokenValidity;
        this.additionalInformation = additionalInformation;
        this.autoApprove = autoApprove;
        this.ui = ui;
    }

}
