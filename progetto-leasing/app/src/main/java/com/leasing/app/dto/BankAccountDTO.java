package com.leasing.app.dto;
import lombok.Data;
import java.math.BigDecimal;
import java.util.List;

@Data
public class BankAccountDTO {
    private String iban;
    private BigDecimal saldo;
    private Long client;
    private List<Long> moviments;
}
