package com.soft.cli.oauthservice.core.repository;

import com.soft.cli.oauthservice.core.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {
    List<Permission> findByCurrentTrue();

    Permission findOneByUserIdToAndCurrentTrue(Integer userIdTo);

    @Query(" select p from Permission p " +
            "where p.deadline <= ?1 and p.current=true ")
    List<Permission> findPermissionCurrentTrue(Date now);

}
