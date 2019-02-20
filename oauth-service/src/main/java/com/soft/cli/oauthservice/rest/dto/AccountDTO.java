package com.soft.cli.oauthservice.rest.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@ToString(exclude = {"roles"})
public class AccountDTO {
    private Boolean clientOnly;
    private List<String> roles;
    private String userName;

}
