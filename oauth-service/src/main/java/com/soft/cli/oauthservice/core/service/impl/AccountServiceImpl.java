package com.soft.cli.oauthservice.core.service.impl;

import com.soft.cli.common.exceptions.util.Check;
import com.soft.cli.oauthservice.core.entity.Account;
import com.soft.cli.oauthservice.core.entity.Authority;
import com.soft.cli.oauthservice.core.entity.Permission;
import com.soft.cli.oauthservice.core.entity.enumeration.UserRole;
import com.soft.cli.oauthservice.core.repository.AccountRepository;
import com.soft.cli.oauthservice.core.repository.AuthorityRepository;
import com.soft.cli.oauthservice.core.service.AccountService;
import com.soft.cli.oauthservice.core.service.PermissionService;
import com.soft.cli.oauthservice.core.util.SingleUtil;
import com.soft.cli.oauthservice.rest.rbc.RbcAccount;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
@Transactional
public class AccountServiceImpl implements AccountService {
    private final Logger log = LoggerFactory.getLogger(AccountServiceImpl.class);
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorityRepository;
    private final PermissionService permissionService;

    @Autowired
    public AccountServiceImpl(final AccountRepository accountRepository,
                              final AuthorityRepository authorityRepository,
                              PermissionService permissionService) {
        this.accountRepository = accountRepository;
        this.authorityRepository = authorityRepository;
        this.permissionService = permissionService;
    }

    @Override
    public Account findOne(String userName) {
        return accountRepository.findByUsername(userName);
    }

    @Override
    public Account create(RbcAccount rbcAccount) {
        log.debug("RbcAccount {} ",rbcAccount.toString());
        Account account = new Account();
        account.setId(rbcAccount.getId());
        account.setUserName(rbcAccount.getName());
        SingleUtil singleUtil = SingleUtil.getInstance();
        String encryptPassword = singleUtil.encryptPassword(rbcAccount.getPassword());
        log.debug("password : {} ", encryptPassword);
        account.setPassword(rbcAccount.getPassword());
        account.setEnabled(Boolean.TRUE);
        Authority authority = authorityRepository.getOne(UserRole.ROLE_USER.getName());
        List<Authority> authorities = new ArrayList<>();
        authorities.add(authority);
        account.setAuthorities(authorities);
        account = accountRepository.save(account);
        log.debug("User Created : {}",account);
        return account;
    }

    @Override
    public Account update(String name, RbcAccount rbcAccount) {
        Account account = accountRepository.findByUsername(rbcAccount.getName());
        Check.notNull(account,"error.noData",rbcAccount.getName());
        //SingleUtil singleUtil = SingleUtil.getInstance();
        //String encryptPassword = singleUtil.encryptPassword(rbcAccount.getPassword());
        log.debug("password {} ",rbcAccount.getPassword());
        account.setPassword(rbcAccount.getPassword());
        return accountRepository.save(account);
    }

    @Override
    public void delete(String name) {
        Account account = accountRepository.findByUsername(name);
        Check.notNull(account,"error.noData", name);
        account.setEnabled(true);
        accountRepository.save(account);
    }

    @Override
    public Account findOnePermission(String name) {
        Account account = accountRepository.findByUsername(name);
        Check.notNull(account,"error.noData", name);
        Permission permission = permissionService.findOneUserIdToAndCurrentTrue(account.getId());
        Check.notNull(permission,"error.noData", name);
        return accountRepository.findOneById(permission.getUserIdFrom());
    }

    @Override
    public Boolean findOnePermissionActive(String userName) {
        Account account = accountRepository.findByUsername(userName);
        Boolean i = Boolean.FALSE;
        Check.notNull(account,"error.noData", userName);
        if (account.getAuthorities().stream().anyMatch(authority -> Objects.equals(authority.getName(),"ROLE_PENALTY"))){
            i = Boolean.TRUE;
        }
        Check.isTrue(!i,"error.noData", userName);
        return i;
    }
}
