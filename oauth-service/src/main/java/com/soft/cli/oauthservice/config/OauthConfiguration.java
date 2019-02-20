package com.soft.cli.oauthservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.config.annotation.configurers.ClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.AuthorizationServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerEndpointsConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configurers.AuthorizationServerSecurityConfigurer;
import org.springframework.security.oauth2.provider.code.AuthorizationCodeServices;
import org.springframework.security.oauth2.provider.code.JdbcAuthorizationCodeServices;
//import org.springframework.security.oauth2.provider.token.DefaultTokenServices;
import org.springframework.security.oauth2.provider.token.store.JdbcTokenStore;

import javax.sql.DataSource;

@Configuration
@EnableAuthorizationServer
public class OauthConfiguration extends AuthorizationServerConfigurerAdapter {
    private final AuthenticationManager authenticationManager;
    private final OAuth2RefreshConfig.OauthUserDetailsService oauthUserDetailsService;
    private final DataSource dataSource;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public OauthConfiguration(AuthenticationManager authenticationManager,
                              OAuth2RefreshConfig.OauthUserDetailsService oauthUserDetailsService,
                              DataSource dataSource) {
        this.authenticationManager = authenticationManager;
        this.oauthUserDetailsService = oauthUserDetailsService;
        this.dataSource = dataSource;
    }

    @Bean
    public JdbcTokenStore tokenStore() {
        return new JdbcTokenStore(dataSource);
    }

    @Bean
    protected AuthorizationCodeServices authorizationCodeServices() {
        return new JdbcAuthorizationCodeServices(dataSource);
    }

    @Override
    public void configure(AuthorizationServerSecurityConfigurer security)
            throws Exception {
        //security.passwordEncoder(passwordEncoder);
        security.passwordEncoder(passwordEncoder)
                .tokenKeyAccess("permitAll()")
                .checkTokenAccess("isAuthenticated()")
        ;
    }

    @Override
    public void configure(AuthorizationServerEndpointsConfigurer endpoints)
            throws Exception {
        endpoints.authorizationCodeServices(authorizationCodeServices())
                .authenticationManager(authenticationManager)
                //.reuseRefreshTokens(true)
                .tokenStore(tokenStore())
                .userDetailsService(oauthUserDetailsService)
                //.tokenStore(new JdbcTokenStore(dataSource))
                .approvalStoreDisabled()
        ;
    }

    @Override
    public void configure(ClientDetailsServiceConfigurer clients) throws Exception {
        clients.jdbc(dataSource)
                .passwordEncoder(passwordEncoder)
                /*.withClient("client")
                .authorizedGrantTypes("authorization_code",
                        "refresh_token","password", "implicit")
                .authorities("ROLE_CLIENT")
                .resourceIds("apis")
                .scopes("read", "write")
                .secret("secret")
                .accessTokenValiditySeconds(3600)//900 15 min, 3600 60 min
                .refreshTokenValiditySeconds(3600)
                .and()
                .withClient("saleUi")
                .authorizedGrantTypes("authorization_code",
                        "refresh_token","password", "implicit")
                .authorities("ROLE_CLIENT")
                .resourceIds("apis")
                .scopes("read", "write")
                .secret("secretSaleUi")
                .accessTokenValiditySeconds(3600)//900 15 min, 3600 60 min
                .refreshTokenValiditySeconds(3600)
                .and()
                .withClient("client-pnr-soft")
                .authorizedGrantTypes("client_credentials", "authorization_code",
                        "refresh_token", "implicit")
                .authorities("ROLE_CLIENT", "ROLE_PNR_CLIENT")
                .scopes("read", "write", "pnr")
                .resourceIds("oauth2-resource")
                .secret("client-pnr-soft")
                .accessTokenValiditySeconds(-1)
                .and()
                .withClient("my-client-with-registered-redirect")
                .authorizedGrantTypes("authorization_code")
                .authorities("ROLE_CLIENT").scopes("read", "trust")
                .resourceIds("oauth2-resource")
                .redirectUris("http://anywhere?key=value")
                .and()
                .withClient("my-client-with-secret")
                .authorizedGrantTypes("client_credentials", "password")
                .authorities("ROLE_CLIENT").scopes("read")
                .resourceIds("oauth2-resource").secret("secret")*/
        ;
    }
    /*@Bean
    public DefaultTokenServices tokenServices() {
        DefaultTokenServices tokenServices = new DefaultTokenServices();
        tokenServices.setSupportRefreshToken(true);
        tokenServices.setTokenStore(this.tokenStore());
        return tokenServices;
    }*/
}
