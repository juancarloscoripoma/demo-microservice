package com.soft.cli.oauthservice.core.service;

import com.soft.cli.oauthservice.core.entity.ApplicationClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ApplicationClientService {
    List<ApplicationClient> findAllByUiTrue();
    List<ApplicationClient> findAll();
    List<ApplicationClient> findAllByUiFalse();
    Page<ApplicationClient> findAllPage(Pageable pageable);
}
