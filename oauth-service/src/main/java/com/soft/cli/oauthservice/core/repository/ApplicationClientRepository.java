package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.ApplicationClient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ApplicationClientRepository extends JpaRepository<ApplicationClient,String> {
    List<ApplicationClient> findAllByUiIsTrue();
    List<ApplicationClient> findAllByUiIsFalse();
}
