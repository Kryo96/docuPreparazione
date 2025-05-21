package com.leasing.app.model.common.cash;

import com.leasing.app.model.Client;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String iban;

    private BigDecimal saldo;

    @OneToOne
    @JoinColumn(name = "client_id")
    private Client client;


    @OneToMany(mappedBy = "bankAccount", cascade = CascadeType.ALL)
    private List<Accounting> moviments;
}
