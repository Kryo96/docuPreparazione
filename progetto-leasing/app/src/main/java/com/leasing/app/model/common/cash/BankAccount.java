package com.leasing.app.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
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
    @JoinColumn(name = "cliente_id")
    private Client client;


    @OneToMany(mappedBy = "contoBancario", cascade = CascadeType.ALL)
    private List<MovimentoContabile> movimenti;
}
