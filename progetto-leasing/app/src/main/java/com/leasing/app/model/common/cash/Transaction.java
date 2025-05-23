package com.leasing.app.model.common.cash;

import com.leasing.app.model.Product;
import jakarta.persistence.*;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "transactions")
public class Transaction {
    @Id
    @GeneratedValue
    private Long id;

    private Timestamp data;
    private BigDecimal importo;
    private String descrizione;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product productId;

    @ManyToOne
    @JoinColumn(name = "bank_account_id")
    private BankAccount bankAccountId;
}
