package com.soft.cli.oauthservice.config.language;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.MessageSourceResolvable;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Optional;

@Component
public class MessageByLocaleServiceImpl implements MessageByLocaleService{
    private final Logger log = LoggerFactory.getLogger(MessageByLocaleServiceImpl.class);
    private static final List<Locale> LOCALES = Arrays.asList( new Locale("en"), new Locale("es"));
    @Autowired
    private MessageSource messageSource;

    @Override
    public String getMessage(String id) {
        Locale locale= localeHeader();
        log.debug("Locate language {} ",locale);
        return messageSource.getMessage(id,null,locale);
    }

    @Override
    public String getMessage(String id, Object[] arg) {
        Locale locale= localeHeader();
        log.debug("Locate language {} ",locale);
        return messageSource.getMessage(id,arg,locale);
    }

    @Override
    public String getMessage(MessageSourceResolvable resolvable) {
        Locale locale= localeHeader();
        log.debug("Locate language {} ",locale);
        return messageSource.getMessage(resolvable,locale);
    }

    private Locale localeHeader(){
        ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest httpServletRequest = attr.getRequest();
        if (httpServletRequest.getHeader("Accept-Language")!=null) {
            Optional<Locale> locale = LOCALES.stream().filter(m->m.equals(new Locale(httpServletRequest.getHeader("Accept-Language")))).findFirst();
            return locale.orElseGet(Locale::getDefault);
        }
        return Locale.getDefault();
    }
}
