package com.leasing.app.dto;
import lombok.Data;

import java.util.List;

@Data
public class BankAccountDTO {
    private String iban;
    private String intestatario;
    private Long client;
    private List<Long> moviments;
}
