package com.leasing.app.model;

import com.leasing.app.model.common.cash.BankAccount;
import jakarta.persistence.*;

@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;
    private String uniqueStringForIdentity;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private BankAccount bankAccount;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUniqueStringForIdentity(String uniqueStringForIdentity) {
        this.uniqueStringForIdentity = uniqueStringForIdentity;
    }

    public String getUniqueStringForIdentity() {return uniqueStringForIdentity;}

}