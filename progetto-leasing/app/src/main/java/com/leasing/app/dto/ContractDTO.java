package com.leasing.app.dto;

import lombok.Data;

import java.sql.Time;
import java.sql.Timestamp;

@Data
public class ContractDTO {

    private String contractNumber;
    private Timestamp start_date;
    private Timestamp end_date;
    private Long Client_id;
}
