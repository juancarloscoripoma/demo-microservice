package com.soft.cli.oauthservice.core.service;

import com.soft.cli.oauthservice.core.entity.Account;
import com.soft.cli.oauthservice.rest.rbc.RbcAccount;

public interface AccountService {
    Account findOne(String userName);
    Account create(RbcAccount rbcAccount);
    Account update(String name, RbcAccount rbcAccount);
    void delete(String userName);
    Account findOnePermission(String userName);
    Boolean findOnePermissionActive(String userName);
}
