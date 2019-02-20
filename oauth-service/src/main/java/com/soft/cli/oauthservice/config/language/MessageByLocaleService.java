package com.soft.cli.oauthservice.config.language;

import org.springframework.context.MessageSourceResolvable;

public interface MessageByLocaleService {
    String getMessage(String id);
    String getMessage(String id, Object[] arg);
    String getMessage(MessageSourceResolvable resolvable);
}
