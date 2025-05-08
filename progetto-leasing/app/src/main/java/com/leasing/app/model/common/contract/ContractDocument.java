package com.leasing.app.model.common.contract;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
public class ContractDocument {

    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String type;
    private LocalDate uploadDate;

    @ManyToOne
    @JoinColumn(name = "contract_id", nullable = false)
    private LeasingContract contract;

    @Version
    private Integer version;

    @PrePersist
    @PreUpdate
    public void setDefaultValues() {
        if (uploadDate == null) {
            uploadDate = LocalDate.now();
        }
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDate getUploadDate() {
        return uploadDate;
    }

    public void setUploadDate(LocalDate uploadDate) {
        this.uploadDate = uploadDate;
    }

    public LeasingContract getContract() {
        return contract;
    }

    public void setContract(LeasingContract contract) {
        this.contract = contract;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

}
