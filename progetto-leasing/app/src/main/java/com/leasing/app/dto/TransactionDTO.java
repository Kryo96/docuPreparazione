package com.leasing.app.dto;
import lombok.Data;

import java.math.BigDecimal;
import java.sql.Timestamp;

@Data
public class TransactionDTO {
    private Timestamp data;
    private BigDecimal importo;
    private String descrizione;
    private Long productId;
    private Long bankAccountId;
}
