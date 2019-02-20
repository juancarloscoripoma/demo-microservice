package com.soft.cli.common.lang;

import org.springframework.context.MessageSourceResolvable;

import java.util.Locale;

public interface MessageByLocaleService {
    String getMessage(String id);
    String getMessage(String id, Object... arg);
    String get(String id, Object... args);
    String get(Locale loc, String id, Object... args);
    String getMessage(MessageSourceResolvable resolvable);
}
