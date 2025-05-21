package com.leasing.app.model.common.contract;

import com.leasing.app.model.Client;
import com.leasing.app.model.Product;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
@Entity(name="product_contract")
public abstract class ProductContract {

    @EmbeddedId
    private ProductContractId productContractId;

    @MapsId("productId")
    @ManyToOne
    @JoinColumn(name = "productid", referencedColumnName = "id", nullable = false)
    private Product product;

    @MapsId("contractId")
    @ManyToOne
    @JoinColumn(name = "contractid", referencedColumnName = "id", nullable = false)
    private Contract contract;

    @Version
    private Integer version;
}