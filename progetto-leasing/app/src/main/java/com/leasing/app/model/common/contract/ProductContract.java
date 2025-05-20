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



    @MapsId("product_id")
    @ManyToOne
    private Product productId;

    @MapsId("contract_id")
    @ManyToOne
    private Contract contractId;

    @Version
    private Integer version;

    }
