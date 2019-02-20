package com.soft.cli.oauthservice.rest.rbc;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class RbcPermission {
    @Min(0) @Max(2147483647)
    private Integer userIdFrom;

    @Min(0) @Max(2147483647)
    private Integer userIdTo;

    private Boolean current;

}
