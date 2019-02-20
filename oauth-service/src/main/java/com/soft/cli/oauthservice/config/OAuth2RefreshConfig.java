package com.soft.cli.oauthservice.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.util.Assert;

import java.util.LinkedList;
import java.util.List;

@Configuration
public class OAuth2RefreshConfig {
    private static final Logger log = LoggerFactory.getLogger(OAuth2RefreshConfig.class);
    private static final String ERROR_OAUTH2REFRESH = "Error refresh token";

    @Bean
    public OauthUserDetailsService userDetailsService() {
        log.debug("OauthUserDetailsService");
        return new OauthUserDetailsService();
    }

    public static class OauthUserDetailsService implements UserDetailsService {
        private List<UserDetailsService> uds = new LinkedList<>();

        public void addService(UserDetailsService srv) {
            uds.add(srv);
        }

        @Override
        public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
            if (uds != null) {
                for (UserDetailsService srv : uds) {
                    try {
                        final UserDetails details = srv.loadUserByUsername(login);
                        if (details != null) {
                            return details;
                        }
                    } catch (UsernameNotFoundException ex) {
                        log.debug("UsernameNotFoundException {} ",ex.getMessage());
                        Assert.notNull(ex,ERROR_OAUTH2REFRESH);
                    } catch (Exception ex) {
                        log.debug("Exception loadUserByUsername: {} ",ex.getMessage());
                        throw ex;
                    }
                }
            }
            throw new UsernameNotFoundException("Unknown user");
        }
    }
}
