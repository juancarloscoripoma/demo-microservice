package com.soft.cli.oauthservice.rest.rbc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RbcAccount {
    @Size(min = 1, max = 50)
    private String name;
    @Size(min = 1, max = 150)
    private String password;
    private Boolean enabled;
    @Min(0) @Max(2147483647)
    private Integer id;

}
