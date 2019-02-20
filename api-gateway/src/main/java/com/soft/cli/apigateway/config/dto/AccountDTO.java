package com.soft.cli.apigateway.config.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class AccountDTO {
    private Boolean clientOnly;
    private List<String> roles;
    private String userName;
}
