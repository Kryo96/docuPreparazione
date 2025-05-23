package com.leasing.app.dto;
import lombok.Data;

@Data
public class ClientDTO {
    private String vatnumber;
    private String phonenumber;
    private String name;
    private String email;
    private String uniqueStringForIdentity;
    private Long webUser;
    private Long bankAccount;
}
