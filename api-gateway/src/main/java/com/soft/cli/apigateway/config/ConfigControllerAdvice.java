package com.soft.cli.apigateway.config;

import com.soft.cli.common.exceptions.ControllerExceptionAdvice;
import com.soft.cli.common.lang.MessageByLocaleService;
import com.soft.cli.common.lang.MessageByLocaleServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConfigControllerAdvice {

    @Bean
    public ControllerExceptionAdvice controllerExceptionAdvice(){
        return new ControllerExceptionAdvice(messageByLocaleService());
    }

    @Bean
    public MessageByLocaleService messageByLocaleService(){
        return new MessageByLocaleServiceImpl();
    }

}
