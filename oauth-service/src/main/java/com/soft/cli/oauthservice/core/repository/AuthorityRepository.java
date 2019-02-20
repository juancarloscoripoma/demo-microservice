package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.Authority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority,String> {
}
