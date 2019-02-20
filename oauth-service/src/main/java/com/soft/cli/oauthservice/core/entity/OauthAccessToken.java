package com.soft.cli.oauthservice.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "oauth_access_token")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"oauthRefreshTokens"})
public class OauthAccessToken implements Serializable {

    @Id
    @NotNull
    @Size(min = 8, max = 256, message = "token unique")
    @Column(unique = true, name = "tokenId")
    private String tokenId;

    @Lob
    @Column(name = "token", nullable = false)
    private String token;

    @Size(min = 0,max = 256)
    @Column(name = "authenticationId",length = 256)
    private String authenticationId;

    @Size(min = 0,max = 100)
    @Column(name = "user_name",length = 100)
    private String userName;

    @Size(min = 0,max = 256)
    @Column(name = "clientId",length = 256)
    private String clientId;

    @Lob
    @Column(name = "authentication", nullable = false)
    private byte[] authentication;

    @Size(min = 0,max = 256)
    @Column(name = "refreshToken",length = 256)
    private String refreshToken;

    @OneToMany
    @JoinColumn(name="token_id", referencedColumnName="refreshToken")
    private List<OauthRefreshToken> oauthRefreshTokens = new ArrayList<OauthRefreshToken>();

}
