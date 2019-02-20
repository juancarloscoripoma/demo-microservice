package com.soft.cli.oauthservice.core.service.impl;

import com.soft.cli.oauthservice.core.entity.Account;
import com.soft.cli.oauthservice.core.entity.Authority;
import com.soft.cli.oauthservice.core.entity.Permission;
import com.soft.cli.oauthservice.core.entity.enumeration.UserRole;
import com.soft.cli.oauthservice.core.repository.AccountRepository;
import com.soft.cli.oauthservice.core.repository.AuthorityRepository;
import com.soft.cli.oauthservice.core.repository.PermissionRepository;
import com.soft.cli.oauthservice.core.service.PermissionService;
import com.soft.cli.oauthservice.rest.rbc.RbcPermission;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Service
@Transactional
public class PermissionServiceImpl implements PermissionService {
    private final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
    private final PermissionRepository permissionRepository;
    private final AccountRepository accountRepository;
    private final AuthorityRepository authorizationRepository;


    @Autowired
    public PermissionServiceImpl(final PermissionRepository permissionRepository,
                                 final AccountRepository accountRepository,
                                 final AuthorityRepository authorizationRepository) {
        this.permissionRepository = permissionRepository;
        this.accountRepository =  accountRepository;
        this.authorizationRepository = authorizationRepository;
    }

    @Override
    public List<Permission> findAllActives() {
        return permissionRepository.findByCurrentTrue();
    }

    @Override
    public Permission create(RbcPermission rbcPermission) {
        Calendar c = Calendar.getInstance();
        c.add(Calendar.MINUTE, 10);

        Permission permission = new Permission();
        permission.setUserIdTo(rbcPermission.getUserIdTo());
        permission.setUserIdFrom(rbcPermission.getUserIdFrom());
        permission.setCreatedDate(new Date());
        permission.setDeadline(c.getTime());
        permission.setCurrent(rbcPermission.getCurrent());
        permission = permissionRepository.save(permission);

        Account account = accountRepository.findOneById(rbcPermission.getUserIdTo());
        Authority authority = authorizationRepository.getOne(UserRole.ROLE_PENALTY.getName());
        account.getAuthorities().add(authority);
        accountRepository.save(account);
        return permission;
    }

    @Override
    public Permission findOneUserIdToAndCurrentTrue(Integer userId) {
        return permissionRepository.findOneByUserIdToAndCurrentTrue(userId);
    }

    @Scheduled(fixedRate = 300000)
    public void closedPenaltyPermissions() {
        log.debug("closedPenaltyPermissions");
        List<Permission> permissions = permissionRepository.findPermissionCurrentTrue(new Date());
        permissions.forEach(permission ->{
            permission.setCurrent(false);
            Account account = accountRepository.findOneById(permission.getUserIdTo());
            Authority authority = authorizationRepository.getOne(UserRole.ROLE_PENALTY.getName());
            account.getAuthorities().remove(authority);
            accountRepository.save(account);
            permissionRepository.save(permission);
        });

    }
}
