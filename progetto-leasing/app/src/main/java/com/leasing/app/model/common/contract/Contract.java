package com.leasing.app.model.common.contract;

import com.leasing.app.model.Client;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Contract {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "contractnumber", unique = true)
    private String contractNumber;
    private Timestamp startDate;
    private Timestamp endDate;
    private Boolean isActive;

    @ManyToOne
    private Client client;
    public boolean isActive() {
        return LocalDate.now().isBefore(endDate.toLocalDateTime().toLocalDate());
    }

    @PrePersist
    @PreUpdate
    public void generateContractCode() {
        if (contractNumber == null) {
            contractNumber = "CONTRACT-" + System.currentTimeMillis();
        }
    }

    @PreRemove
    public void deactivateContract() {
        this.isActive = false;
    }

}
