package com.soft.cli.oauthservice.core.service;

import com.soft.cli.oauthservice.core.entity.Permission;
import com.soft.cli.oauthservice.rest.rbc.RbcPermission;

import java.util.List;

public interface PermissionService {
    List<Permission> findAllActives();
    Permission create(RbcPermission rbcPermission);
    Permission findOneUserIdToAndCurrentTrue(Integer userId);
}