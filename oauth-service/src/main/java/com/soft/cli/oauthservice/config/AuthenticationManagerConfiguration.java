package com.soft.cli.oauthservice.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.GlobalAuthenticationConfigurerAdapter;

import javax.sql.DataSource;

@Configuration
@Order(Ordered.LOWEST_PRECEDENCE - 20)
public class AuthenticationManagerConfiguration extends GlobalAuthenticationConfigurerAdapter {
    private final Logger log = LoggerFactory.getLogger(AuthenticationManagerConfiguration.class);
    private final DataSource dataSource;
    private final OAuth2RefreshConfig.OauthUserDetailsService uds;

    public AuthenticationManagerConfiguration(DataSource dataSource,
                                              OAuth2RefreshConfig.OauthUserDetailsService uds) {
        this.dataSource = dataSource;
        this.uds = uds;
        log.debug("start..");
    }

    @Override
    public void init(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("connection DB");
        auth.jdbcAuthentication().dataSource(dataSource)
                /*.withUser("carlos@admin.com")
                .password("2bb80d537b1da3e38bd30361aa855686bde0eacd7162fef6a25fe97bf527a25b")
                .roles("USER")
                .and()
                .withUser("juan@admin.com")
                .password("5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8")
                .roles("ADMIN")
                .and()
                .withUser("cori@admin.com")
                .password("8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918")
                .roles("ADMIN")
        .and()*/.getUserDetailsService()
        ;

        uds.addService(auth.getDefaultUserDetailsService());
    }
}
