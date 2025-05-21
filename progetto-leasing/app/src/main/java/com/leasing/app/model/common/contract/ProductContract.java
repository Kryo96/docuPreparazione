package com.leasing.app.model.common.contract;

import com.leasing.app.model.Client;
import com.leasing.app.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity(name="product_contract")
public abstract class ProductContract {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "contract_id")
    private Contract contract;

    @Version
    private Integer version;
}