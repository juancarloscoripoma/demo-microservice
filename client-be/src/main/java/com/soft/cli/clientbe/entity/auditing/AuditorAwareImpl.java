package com.soft.cli.clientbe.entity.auditing;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

public class AuditorAwareImpl implements AuditorAware<String> {
    private final Logger log = LoggerFactory.getLogger(AuditorAwareImpl.class);

    @Override
    public String getCurrentAuditor(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null || !authentication.isAuthenticated()) {
            return null;
        }
        log.debug("user {} ,authentication {} ",authentication.getName(),authentication.getPrincipal());
        return authentication.getName();
    }
}
