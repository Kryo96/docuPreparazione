package com.leasing.app.dto.signup;

import lombok.Data;

@Data
public class SignupDTO {
    private String username;
    private String password;
    private String vatnumber;
    private String phonenumber;
    private String name;
    private String email;
    private String uniqueStringForIdentity;
}
