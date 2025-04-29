package com.preparazione.preparazione.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class WebsiteUser {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    public WebsiteUser(){}

    private String name;
    private String email;
    private String username;
    private String password;
    @Enumerated(EnumType.STRING)
    private Role role;


}
