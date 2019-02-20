package com.soft.cli.oauthservice.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "oauth_client_details")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"applicationClient"})
public class OauthClientDetails {
    @NotNull
    @Size(min = 2, max = 256)
    @Id
    @Column(name = "clientId",length = 256)
    private String client;

    @Size(min = 2,max = 256)
    @Column(name = "resourceIds",length = 256)
    private String name;

    @Size(min = 0,max = 256)
    @Column(name = "clientSecret",length = 256)
    private String clientSecret;

    @Size(min = 2, max = 256)
    @Column(name = "scope",length = 256)
    private String scope;

    @Size(min = 2,max = 256)
    @Column(name = "authorizedGrantTypes",length = 256)
    private String authorizedGrantTypes;

    @Size(min = 0,max = 256)
    @Column(name = "webServerRedirectUri",length = 256)
    private String webServerRedirectUrl;

    @Size(min = 2, max = 256)
    @Column(name = "authorities",length = 256)
    private String authorities;

    @Column(name = "accessTokenValidity")
    private Integer accessTokenValidity;

    @Column(name = "refreshTokenValidity")
    private Integer refreshTokenValidity;

    @Size(min = 2,max = 4096)
    @Column(name = "additionalInformation",length = 4096)
    private String additionalInformation;

    @Size(min = 0, max = 256)
    @Column(name = "autoapprove",length = 256)
    private String autoApprove;

    @OneToOne(mappedBy = "oauthClientDetails")
    private ApplicationClient applicationClient;

}
