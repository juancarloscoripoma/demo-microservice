package com.soft.cli.oauthservice.config;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.session.SessionRegistryImpl;

import javax.servlet.http.HttpServletResponse;

@Configuration
@Order(SecurityProperties.ACCESS_OVERRIDE_ORDER)
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final Logger log = LoggerFactory.getLogger(SecurityConfiguration.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    /*@Autowired
    private CommonPasswordEncoder commonPasswordEncoder;  //password Encoder*/

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.exceptionHandling()
                .authenticationEntryPoint((request, response,
                                           authException) -> response.sendError(HttpServletResponse.SC_UNAUTHORIZED))
                .and()
                .csrf()
                .disable()
                .headers()
                .frameOptions()
                .disable()
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .authorizeRequests()
                .antMatchers("/oauth/token").permitAll()
                .antMatchers("/api/account/update**").permitAll() //active filter
                .anyRequest().authenticated()
                .and()
                .requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access", "/api/account/update") //add path
        ;

			/*http.formLogin()
					.and()
					.requestMatchers().antMatchers("/login", "/oauth/authorize", "/oauth/confirm_access")
					.and()
					.authorizeRequests().anyRequest().authenticated();
			http.sessionManagement()
					.maximumSessions(1)
					.expiredUrl("/")
					.maxSessionsPreventsLogin(true)
					.sessionRegistry(sessionRegistry());
			http.csrf().disable()*/;
    }

    @Bean
    public SessionRegistry sessionRegistry() {
        return new SessionRegistryImpl();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        log.debug("AuthenticationManagerBuilder : {}", auth);
        auth.parentAuthenticationManager(authenticationManager);
    }
}
