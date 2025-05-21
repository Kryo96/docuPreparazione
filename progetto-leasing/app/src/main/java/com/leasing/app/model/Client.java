package com.leasing.app.model;

import com.leasing.app.model.common.cash.BankAccount;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue
    private Long id;
    private String vatnumber;
    private String phonenumber;
    private String name;
    private String email;
    private String uniqueStringForIdentity;

    @OneToOne
    @JoinColumn(name = "webuser_id")  // questa è la colonna FK nella tabella client
    private WebUser webUser;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL)
    private BankAccount bankAccount;


}