package com.soft.cli.oauthservice.core.service.impl;

import com.soft.cli.oauthservice.core.entity.ApplicationClient;
import com.soft.cli.oauthservice.core.entity.OauthClientDetails;
import com.soft.cli.oauthservice.core.repository.ApplicationClientRepository;
import com.soft.cli.oauthservice.core.repository.OauthClientDetailsRepository;
import com.soft.cli.oauthservice.core.service.OauthClientDetailsService;
import com.soft.cli.oauthservice.rest.dto.OauthClientDetailsDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OauthClientDetailsServiceImpl implements OauthClientDetailsService {
    private final Logger log = LoggerFactory.getLogger(OauthClientDetailsServiceImpl.class);
    private final OauthClientDetailsRepository oauthClientDetailsRepository;
    private final ApplicationClientRepository applicationClientRepository;
    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public OauthClientDetailsServiceImpl(OauthClientDetailsRepository oauthClientDetailsRepository,
                                         ApplicationClientRepository applicationClientRepository) {
        this.oauthClientDetailsRepository = oauthClientDetailsRepository;
        this.applicationClientRepository = applicationClientRepository;
    }

    @Override
    public List<OauthClientDetailsDTO> getAll() {
        List<OauthClientDetails> oauthClientDetails = oauthClientDetailsRepository.findAll();
        return oauthClientDetails.stream().map(o->new OauthClientDetailsDTO(o.getClient(),o.getName(),
                o.getApplicationClient().getName(),o.getApplicationClient().getClientSecret(),o.getScope(),
                o.getAuthorizedGrantTypes(),o.getWebServerRedirectUrl(),o.getAuthorities(),o.getAccessTokenValidity(),
                o.getRefreshTokenValidity(),o.getAdditionalInformation(),o.getAutoApprove(),
                o.getApplicationClient().getUi())).collect(Collectors.toList());
    }

    @Override
    public OauthClientDetailsDTO getOne(String client) {
        OauthClientDetails o = oauthClientDetailsRepository.findOne(client);
        return new OauthClientDetailsDTO(o.getClient(),o.getName(),
                o.getApplicationClient().getName(),o.getApplicationClient().getClientSecret(),o.getScope(),
                o.getAuthorizedGrantTypes(),o.getWebServerRedirectUrl(),o.getAuthorities(),o.getAccessTokenValidity(),
                o.getRefreshTokenValidity(),o.getAdditionalInformation(),o.getAutoApprove(),
                o.getApplicationClient().getUi());
    }

    @Override
    @Transactional
    public OauthClientDetailsDTO create(OauthClientDetailsDTO oauthClientDetailsDTO) {
        log.debug("secret password {} ",passwordEncoder.encode(oauthClientDetailsDTO.getClientSecret()));
        OauthClientDetails oauthClientDetails = oauthClientDetailsRepository.findOne(oauthClientDetailsDTO.getClient());
        //Check.isNull(oauthClientDetails,"error.duplicate",oauthClientDetailsDTO.getClient());

        oauthClientDetails = new OauthClientDetails();
        oauthClientDetails.setAccessTokenValidity(oauthClientDetailsDTO.getAccessTokenValidity());
        oauthClientDetails.setAdditionalInformation(oauthClientDetailsDTO.getAdditionalInformation());
        oauthClientDetails.setAuthorities(oauthClientDetailsDTO.getAuthorities());
        oauthClientDetails.setAuthorizedGrantTypes(oauthClientDetailsDTO.getAuthorizedGrantTypes());
        oauthClientDetails.setAutoApprove(oauthClientDetailsDTO.getAutoApprove());
        oauthClientDetails.setClient(oauthClientDetailsDTO.getClient());
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetailsDTO.getClientSecret()));
        oauthClientDetails.setName(oauthClientDetailsDTO.getName());
        oauthClientDetails.setRefreshTokenValidity(oauthClientDetailsDTO.getRefreshTokenValidity());
        oauthClientDetails.setScope(oauthClientDetailsDTO.getScope());
        oauthClientDetails.setWebServerRedirectUrl(oauthClientDetailsDTO.getWebServerRedirectUrl());
        oauthClientDetailsRepository.save(oauthClientDetails);
        ApplicationClient applicationClient = new ApplicationClient();
        applicationClient.setClient(oauthClientDetailsDTO.getClient());
        applicationClient.setClientSecret(oauthClientDetailsDTO.getClientSecret());
        applicationClient.setName(oauthClientDetailsDTO.getNameUi());
        applicationClient.setUi(oauthClientDetailsDTO.getUi());
        applicationClient.setOauthClientDetails(oauthClientDetails);
        applicationClientRepository.save(applicationClient);
        return oauthClientDetailsDTO;
    }

    @Override
    @Transactional
    public OauthClientDetailsDTO update(OauthClientDetailsDTO oauthClientDetailsDTO) {
        OauthClientDetails oauthClientDetails = oauthClientDetailsRepository.findOne(oauthClientDetailsDTO.getClient());
        //Check.notNull(oauthClientDetails,"error.noData",oauthClientDetailsDTO.getClient());
        log.debug("secret password {} ",passwordEncoder.encode(oauthClientDetailsDTO.getClientSecret()));
        oauthClientDetails.setAccessTokenValidity(oauthClientDetailsDTO.getAccessTokenValidity());
        oauthClientDetails.setAdditionalInformation(oauthClientDetailsDTO.getAdditionalInformation());
        oauthClientDetails.setAuthorities(oauthClientDetailsDTO.getAuthorities());
        oauthClientDetails.setAuthorizedGrantTypes(oauthClientDetailsDTO.getAuthorizedGrantTypes());
        oauthClientDetails.setAutoApprove(oauthClientDetailsDTO.getAutoApprove());
        //oauthClientDetails.setClient(oauthClientDetailsDTO.getClient());
        oauthClientDetails.setClientSecret(passwordEncoder.encode(oauthClientDetailsDTO.getClientSecret()));
        oauthClientDetails.setName(oauthClientDetailsDTO.getName());
        oauthClientDetails.setRefreshTokenValidity(oauthClientDetailsDTO.getRefreshTokenValidity());
        oauthClientDetails.setScope(oauthClientDetailsDTO.getScope());
        oauthClientDetails.setWebServerRedirectUrl(oauthClientDetailsDTO.getWebServerRedirectUrl());
        oauthClientDetailsRepository.save(oauthClientDetails);

        ApplicationClient applicationClient = applicationClientRepository.findOne(oauthClientDetailsDTO.getClient());
        //applicationClient.setClient(oauthClientDetailsDTO.getClient());
        applicationClient.setClientSecret(oauthClientDetailsDTO.getClientSecret());
        applicationClient.setName(oauthClientDetailsDTO.getNameUi());
        applicationClient.setUi(oauthClientDetailsDTO.getUi());
        applicationClient.setOauthClientDetails(oauthClientDetails);
        applicationClientRepository.save(applicationClient);
        return oauthClientDetailsDTO;
    }

    @Override
    public void delete(String client) {

    }
}
