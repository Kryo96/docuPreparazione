package com.preparazione.preparazione.dto;

import com.preparazione.preparazione.entities.WebsiteUser;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class WebsiteUserDTO {


    private String user;
    private String password;
    private String email;
    private String name;

}
