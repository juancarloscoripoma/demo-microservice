package com.soft.cli.common.exceptions.util;

import com.soft.cli.common.exceptions.InternalException;
import com.soft.cli.common.exceptions.RestException;
import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.client.HttpClientErrorException;

import java.util.Collection;
import java.util.Map;

public final class Check {
    public static void isNull(Object object, String message, Object... args) {
        if (object != null) {
            throw new RestException(message, args);
        }
    }

    public static void isTrue(boolean expression, String message, Object... args) {
        if (!expression) {
            throw new RestException(message, args);
        }
    }

    public static void notNull(Object object, String message, Object... args) {
        if (object == null) {
            throw new RestException(message, args);
        }
    }

    public static void notEmpty(Object[] array, String message, Object... args) {
        if (ObjectUtils.isEmpty(array)) {
            throw new RestException(message, args);
        }
    }

    public static void notEmpty(Collection<?> collection, String message, Object... args) {
        if (CollectionUtils.isEmpty(collection)) {
            throw new RestException(message, args);
        }
    }

    public static void notEmpty(Map<?, ?> map, String message, Object... args) {
        if (CollectionUtils.isEmpty(map)) {
            throw new RestException(message, args);
        }
    }

    public static void notEmpty(String text, String message, Object... args) {
        if (text == null || "".equals(text.trim())) {
            throw new RestException(message, args);
        }
    }

    public static void showError(String message, Object... args) {
            throw new RestException(message, args);
    }

    public static void internal(String message, Object... args) {
        throw new InternalException(message, args);
    }

    public static void code(HttpStatus code) {
        if(code.is4xxClientError()) {
            throw new HttpClientErrorException(code);
        }
    }

}
