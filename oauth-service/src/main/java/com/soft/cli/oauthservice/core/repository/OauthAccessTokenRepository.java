package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.OauthAccessToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthAccessTokenRepository extends JpaRepository<OauthAccessToken,String> {

    @Query("select oat from OauthAccessToken oat join fetch oat.oauthRefreshTokens where oat.userName=:userName ")
    OauthAccessToken findOneByUsername(@Param("userName") String userName);
}
