package com.soft.cli.oauthservice.core.service.impl;

import com.soft.cli.oauthservice.core.entity.ApplicationClient;
import com.soft.cli.oauthservice.core.repository.ApplicationClientRepository;
import com.soft.cli.oauthservice.core.service.ApplicationClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ApplicationClientServiceImpl implements ApplicationClientService {

    private final Logger log = LoggerFactory.getLogger(ApplicationClientServiceImpl.class);
    private final ApplicationClientRepository applicationClientRepository;

    public ApplicationClientServiceImpl(ApplicationClientRepository applicationClientRepository) {
        this.applicationClientRepository = applicationClientRepository;
    }

    @Override
    public List<ApplicationClient> findAllByUiTrue() {
        log.debug("findAllByUiTrue");
        return applicationClientRepository.findAllByUiIsTrue();
    }

    @Override
    public List<ApplicationClient> findAll() {
        log.debug("findAll");
        return applicationClientRepository.findAll();
    }

    @Override
    public List<ApplicationClient> findAllByUiFalse() {
        log.debug("findAllByUiFalse");
        return applicationClientRepository.findAllByUiIsFalse();
    }

    @Override
    public Page<ApplicationClient> findAllPage(Pageable pageable) {
        log.debug("findAll");
        return applicationClientRepository.findAll(pageable);
    }
}
