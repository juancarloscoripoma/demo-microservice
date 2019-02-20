package com.soft.cli.apigateway.oauth.config;

import com.soft.cli.apigateway.config.dto.AccountDTO;
import com.soft.cli.apigateway.core.service.Oauth2Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@Component
@RequestScope
public class OauthSession {
    private final Logger log = LoggerFactory.getLogger(OauthSession.class);
    private AccountDTO accountDTO;
    private final Oauth2Service oauthService;
    private final HttpServletRequest httpServletRequest;

    public OauthSession(Oauth2Service oauthService, HttpServletRequest httpServletRequest) {
        this.oauthService = oauthService;
        this.httpServletRequest = httpServletRequest;
    }

    public AccountDTO getAccountDTO() {
        return accountDTO;
    }

    private void setAccountDTO(AccountDTO accountDTO) {
        this.accountDTO = accountDTO;
    }

    public void setVariable(){
        try {
            AccountDTO accountDTO = oauthService.getAccount(getCookies());
            log.debug("user session: {} ",accountDTO.toString());
            setAccountDTO(accountDTO);
        }catch (Exception e){
            log.debug("ERROR cause:  {} , message: {}",e.getCause(), e.getMessage());
            //throw new InternalException("Authenticate first please......",null);//car common.exceptions.InternalException
        }
    }

    private Map<String,String> getCookies(){
        log.debug("cookies----------------------------------------------");
        Map<String,String> cookies = new HashMap<>();
        Enumeration<String> enumeration = httpServletRequest.getHeaderNames();
        while(enumeration.hasMoreElements()){
            String param = enumeration.nextElement();
            cookies.put(param,httpServletRequest.getHeader(param));
            log.debug("Authorization {} ", httpServletRequest.getHeader(param));
        }
        cookies.forEach((s, s2) -> log.debug(" {} === {} ", s, s2));
        return cookies;
    }

}
