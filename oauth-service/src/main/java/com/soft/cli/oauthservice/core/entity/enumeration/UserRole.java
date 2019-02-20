package com.soft.cli.oauthservice.core.entity.enumeration;


public enum UserRole {
    ROLE_ADMIN("ROLE_ADMIN"),
    ROLE_USER("ROLE_USER"),
    ROLE_PENALTY("ROLE_PENALTY");

    private String name;

    UserRole(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

}
