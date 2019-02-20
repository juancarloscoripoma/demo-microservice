package com.soft.cli.common.exceptions;

import com.soft.cli.common.exceptions.util.RestMessage;
import com.soft.cli.common.lang.MessageByLocaleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerExceptionAdvice {
    private final Logger log = LoggerFactory.getLogger(ControllerExceptionAdvice.class);
    private static final String UNEXPECTED_ERROR = "Exception.unexpected";
    private final MessageByLocaleService messageByLocaleService;

    @Autowired
    public ControllerExceptionAdvice(MessageByLocaleService messageByLocaleService) {
        this.messageByLocaleService = messageByLocaleService;
    }

    @ExceptionHandler(value = RestException.class)
    public ResponseEntity<RestMessage> handleIllegalArgument(RestException ex) {

        String errorMessage = messageByLocaleService.getMessage(ex.getMessage(),ex.getArgs());

        log.debug("errorMessage : {} :  {} ", errorMessage,String.join(",", Arrays.stream(ex.getArgs()).map(Object::toString).collect(Collectors.toList())));
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestMessage> handleArgumentNotValidException(MethodArgumentNotValidException ex) {
        BindingResult result = ex.getBindingResult();
        List<String> errorMessages = result.getAllErrors()
                .stream()
                .map(messageByLocaleService::getMessage)
                .collect(Collectors.toList());
        return new ResponseEntity<>(new RestMessage(errorMessages), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity<RestMessage> handleExceptions(Exception ex) {
        String errorMessage = messageByLocaleService.getMessage(UNEXPECTED_ERROR, null);
        ex.printStackTrace();
        return new ResponseEntity<>(new RestMessage(errorMessage), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
