package com.soft.cli.common.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class InternalException extends RuntimeException {
    @Getter
    private String message;

    @Getter
    private Object[] args;
}