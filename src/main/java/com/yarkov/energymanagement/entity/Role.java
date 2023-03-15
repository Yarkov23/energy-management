package com.yarkov.energymanagement.entity;

import org.springframework.security.core.GrantedAuthority;

import javax.persistence.Embeddable;

@Embeddable
public class Role implements GrantedAuthority {

    private String name;

    public Role(String name) {
        this.name = name;
    }

    public Role() {
    }

    @Override
    public String getAuthority() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
