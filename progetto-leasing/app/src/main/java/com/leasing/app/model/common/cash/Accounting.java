package com.leasing.app.model.common.cash;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
public class Accounting {
    @Id
    @GeneratedValue
    private Long id;

    private LocalDate data;
    private BigDecimal importo;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccount;
}
