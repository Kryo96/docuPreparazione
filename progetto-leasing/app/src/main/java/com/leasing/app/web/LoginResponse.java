package com.leasing.app.web;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {

    private final String token;

    public LoginResponse(String token){
        this.token = token;
    }
}
