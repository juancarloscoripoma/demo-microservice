package com.soft.cli.oauthservice.core.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "oauth_refresh_token")
@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"oauthAccessToken"})
public class OauthRefreshToken implements Serializable {

    private static final long serialVersionUID = 1L;

    @EmbeddedId
    @AttributeOverrides({@AttributeOverride(name = "tokenId",column = @Column(name = "token_id",nullable = false))})
    OauthRefreshTokenPK oauthRefreshTokenPK;

    @Lob
    @Column(name = "token", nullable = false)
    private byte[] token;

    @Lob
    @Column(name = "authentication", nullable = false)
    private byte[] authentication;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "token_id", nullable = false, insertable = false, updatable = false)
    private OauthAccessToken oauthAccessToken;

    public OauthRefreshToken(OauthRefreshTokenPK oauthRefreshTokenPK, byte[] token, byte[] authentication) {
        this.oauthRefreshTokenPK = oauthRefreshTokenPK;
        this.token = token;
        this.authentication = authentication;
    }

}
