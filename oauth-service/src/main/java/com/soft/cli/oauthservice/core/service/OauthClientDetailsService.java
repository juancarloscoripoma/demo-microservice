package com.soft.cli.oauthservice.core.service;

import com.soft.cli.oauthservice.rest.dto.OauthClientDetailsDTO;

import java.util.List;

public interface OauthClientDetailsService {
    List<OauthClientDetailsDTO> getAll();
    OauthClientDetailsDTO getOne(String client);
    OauthClientDetailsDTO create(OauthClientDetailsDTO oauthClientDetailsDTO);
    OauthClientDetailsDTO update(OauthClientDetailsDTO oauthClientDetailsDTO);
    void delete(String client);
}
