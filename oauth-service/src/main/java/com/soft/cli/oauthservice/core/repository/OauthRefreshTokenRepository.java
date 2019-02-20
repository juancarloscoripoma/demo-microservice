package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.OauthRefreshToken;
import com.soft.cli.oauthservice.core.entity.OauthRefreshTokenPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthRefreshTokenRepository extends JpaRepository<OauthRefreshToken, OauthRefreshTokenPK> {
}
