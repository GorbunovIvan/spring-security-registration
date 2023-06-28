package com.example.model;

import org.springframework.security.core.GrantedAuthority;

public enum Authority implements GrantedAuthority {

    READ_BOOKS,
    WRITE_BOOKS;

    @Override
    public String getAuthority() {
        return name();
    }
}
