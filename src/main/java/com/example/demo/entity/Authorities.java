package com.example.demo.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;
@Component
public class Authorities implements GrantedAuthority {
    private String username;
    private String authority;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
}