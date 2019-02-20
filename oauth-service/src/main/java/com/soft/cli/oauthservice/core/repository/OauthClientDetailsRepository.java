package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.OauthClientDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OauthClientDetailsRepository extends JpaRepository<OauthClientDetails,String> {
}
