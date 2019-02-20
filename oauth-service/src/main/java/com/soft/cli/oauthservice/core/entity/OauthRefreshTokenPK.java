package com.soft.cli.oauthservice.core.entity;

import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@NoArgsConstructor
public class OauthRefreshTokenPK implements Serializable {
    private static final long serialVersionUID = 3L;

    @Column(name = "token_id", nullable = false)
    private String tokenId;

    public OauthRefreshTokenPK(String tokenId) {
        this.tokenId = tokenId;
    }

    public String getTokenId() {
        return tokenId;
    }

    public void setTokenId(String tokenId) {
        this.tokenId = tokenId;
    }
}
