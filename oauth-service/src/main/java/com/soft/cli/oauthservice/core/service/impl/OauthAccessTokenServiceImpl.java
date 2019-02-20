package com.soft.cli.oauthservice.core.service.impl;

import com.soft.cli.oauthservice.core.entity.OauthAccessToken;
import com.soft.cli.oauthservice.core.entity.OauthRefreshToken;
import com.soft.cli.oauthservice.core.repository.OauthAccessTokenRepository;
import com.soft.cli.oauthservice.core.repository.OauthRefreshTokenRepository;
import com.soft.cli.oauthservice.core.service.OauthAccessTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OauthAccessTokenServiceImpl implements OauthAccessTokenService {
    private final OauthAccessTokenRepository oauthAccessTokenRepository;
    private final OauthRefreshTokenRepository oauthRefreshTokenRepository;

    @Autowired
    public OauthAccessTokenServiceImpl(final OauthAccessTokenRepository oauthAccessTokenRepository,
                                       final OauthRefreshTokenRepository oauthRefreshTokenRepository) {
        this.oauthAccessTokenRepository = oauthAccessTokenRepository;
        this.oauthRefreshTokenRepository = oauthRefreshTokenRepository;
    }

    @Override
    public void delete(String userName) {
        OauthAccessToken oauthAccessToken = oauthAccessTokenRepository.findOneByUsername(userName);
        List<OauthRefreshToken> oauthRefreshTokens = oauthAccessToken.getOauthRefreshTokens();
        oauthAccessTokenRepository.delete(oauthAccessToken);
        /*oauthRefreshTokens.forEach(oauthRefreshToken -> {
            oauthRefreshTokenRepository.delete(oauthRefreshToken);
        });*/
    }
}
