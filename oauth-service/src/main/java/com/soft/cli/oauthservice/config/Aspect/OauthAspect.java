package com.soft.cli.oauthservice.config.Aspect;

import com.soft.cli.oauthservice.core.util.SingleUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Aspect
@Component
public class OauthAspect {
    private final Logger log = LoggerFactory.getLogger(OauthAspect.class);

    @Pointcut(value = "execution(* org.springframework.security.oauth2.provider.endpoint..*.*(..))")
    public void methodPointcut() {}

    @Pointcut(value = "execution(* org.springframework.security.oauth2.provider.endpoint.TokenEndpoint.*(..))")
    public void methodOauth() {}

    @Before(value = "methodPointcut() && methodOauth()")
    public void oauthAdvice(JoinPoint joinPoint){
        Object[] obj = joinPoint.getArgs();
        if (obj.length>0){
            for (Object a:obj){
                if (a instanceof Map){
                    Map<String,String> oauth=(Map)a;
                    if (oauth.containsKey("password")){
                        log.debug("Aspect  valor {} ",oauth);
                        SingleUtil singleUtil = SingleUtil.getInstance();
                        String oauthPassword=oauth.get("password");
                        String encryptPassword = singleUtil.encryptPassword(oauthPassword);
                        log.debug("password  : {} ",encryptPassword);
                        oauth.put("password",encryptPassword);
                    }
                }
            }
        }
    }

}
