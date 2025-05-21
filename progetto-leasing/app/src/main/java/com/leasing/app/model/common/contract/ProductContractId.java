package com.leasing.app.model.common.contract;

import jakarta.persistence.Embeddable;
import lombok.Data;


@Data
@Embeddable
public class ProductContractId {
    private Long productId;
    private Long contractId;
}
